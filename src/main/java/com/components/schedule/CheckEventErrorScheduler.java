package com.components.schedule;

import com.components.entities.LoggingEvent;
import com.components.entities.MailReceiver;
import com.components.service.MailReceiverService;
import com.components.service.SystemLogService;
import com.components.util.MailSendHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 检查接口报错定时任务
 * Created by wish on 2017/7/18.
 */
@Component
public class CheckEventErrorScheduler {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SystemLogService systemLogService;

    @Autowired
    private MailReceiverService mailReceiverService;

    @Autowired
    private MailSendHandler mailSendHandler;

    /**
     * 错误数警戒值，大于等于该值发送邮件
     */
    @Value("${components.mail.error_num_to_send_mail}")
    private String errorNumToSendMail;

    @Value("${components.mail.app.name}")
    private String emailAppName;

    //邮件中最大展示日志数量
    private final int maxEmailEventNum = 100;

    @Scheduled(cron = "1 0/20 * * * ?") // 每20分钟执行
    public void checkEventError() {
        logger.info("开始检查错误日志");
        //每次检查最近30分钟内的错误日志
       	int checkMinute = 30;

        //最近30分钟内的访问第三方报错的日志
        List<LoggingEvent> apiErrorEventList = systemLogService.findApiErrorEventPeriod(checkMinute);
        if(apiErrorEventList.size() >= Integer.valueOf(errorNumToSendMail)) {
            //收件邮箱地址
            String[] to = getEmailTo();
            if(to.length > 0) {
                //生成邮件内容
                String content = getEmailContent(checkMinute,apiErrorEventList);
                //发送邮件
				mailSendHandler.sendSimpleMail(to,"调用远程接口报错提醒",content);
            }
        }
        logger.info("检查错误日志结束，{}分钟内出现了{}个错误", checkMinute, apiErrorEventList.size());

    }

    /**
     * 获取收件邮箱地址
     * @return
     */
    private String[] getEmailTo() {
        List<MailReceiver> mailReceiverList = mailReceiverService.findAllMailReceivers();
        List<String> toList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(mailReceiverList)) {
            for (MailReceiver mailReceiver : mailReceiverList) {
                toList.add(mailReceiver.getEmail());
            }
        }
        String[] to = new String[toList.size()];
        return toList.toArray(to);
    }

    /**
     * 生成邮件内容
     * @param checkMinute 检查checkMinute分钟内的错误日志
     * @param apiErrorEventList 报错日志
     * @return
     */
    private String getEmailContent(int checkMinute, List<LoggingEvent> apiErrorEventList) {

        if(CollectionUtils.isEmpty(apiErrorEventList)) {
            return "";
        }

        StringBuilder content = new StringBuilder();
        //邮件头部
        content.append(getEmailContentHeader(checkMinute, apiErrorEventList));
        //邮件内容报错详情
        content.append(getEmailContentDetail(apiErrorEventList));
        //邮件尾部
        content.append(getEmailContentFooter(apiErrorEventList));

        return content.toString();
    }

    /**
     * 生成邮件内容头部
     * @param checkMinute 检查checkMinute分钟内的错误日志
     * @param apiErrorEventList 报错日志
     * @return
     */
    private String getEmailContentHeader(int checkMinute, List<LoggingEvent> apiErrorEventList) {
        StringBuilder contentHeader = new StringBuilder();
        contentHeader.append("【项目：").append(emailAppName).append("】最近");
        contentHeader.append(checkMinute);
        contentHeader.append("分钟内访问第三方接口报错频繁，共出现error ");
        contentHeader.append(apiErrorEventList.size());
        contentHeader.append("次。");
        contentHeader.append(System.lineSeparator());
        contentHeader.append(System.lineSeparator());
        return contentHeader.toString();
    }

    /**
     * 生成邮件详情
     * @param apiErrorEventList 报错日志
     * @return
     */
    private String getEmailContentDetail(List<LoggingEvent> apiErrorEventList) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder contentDetail = new StringBuilder();
        int total = maxEmailEventNum < apiErrorEventList.size() ? maxEmailEventNum : apiErrorEventList.size();
        for (int i = 0; i< total; i++) {
            LoggingEvent loggingEvent = apiErrorEventList.get(i);
            contentDetail.append(System.lineSeparator());
            contentDetail.append(System.lineSeparator());
            contentDetail.append(System.lineSeparator());
            contentDetail.append("第"+(i+1)+"次,详情:");
            contentDetail.append("======================================================================");
            contentDetail.append(System.lineSeparator());
            contentDetail.append("异常时间:");
            contentDetail.append(sdf.format(loggingEvent.getTimestmp()));
            contentDetail.append(System.lineSeparator());
            contentDetail.append("线程名称:");
            contentDetail.append(loggingEvent.getThreadName());
            contentDetail.append(System.lineSeparator());
            contentDetail.append("异常信息:");
            contentDetail.append(loggingEvent.getFormattedMessage());
        }
        return contentDetail.toString();
    }

    /**
     * 生成邮件底部
     * @param apiErrorEventList 报错日志
     * @return
     */
    private String getEmailContentFooter(List<LoggingEvent> apiErrorEventList) {
        StringBuilder contentFooter= new StringBuilder();
        if(apiErrorEventList.size() > maxEmailEventNum) {
            contentFooter.append(System.lineSeparator());
            contentFooter.append(System.lineSeparator());
            contentFooter.append("邮件仅展示");
            contentFooter.append(maxEmailEventNum);
            contentFooter.append("条错误信息。");
        }
        try {
            InetAddress addr = InetAddress.getLocalHost();
            String ip=addr.getHostAddress().toString(); //获取本机ip
            String hostName=addr.getHostName().toString(); //获取本机计算机名称

            contentFooter.append(System.lineSeparator());
            contentFooter.append(System.lineSeparator());
            contentFooter.append("来自于IP :" + ip);
            contentFooter.append(System.lineSeparator());
            contentFooter.append(System.lineSeparator());
            contentFooter.append("来自于计算机名称 :"+hostName);
            logger.info(String.valueOf(ip)+""+String.valueOf(hostName));
        } catch (UnknownHostException e) {
            e.printStackTrace();
            logger.error(String.valueOf(e));
        }
        return contentFooter.toString();
    }

}
