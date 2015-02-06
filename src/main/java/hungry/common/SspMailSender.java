/*
 * プロジェクト名 : SSP_MANAGEシステム 開発/保守履歴 : 2014/05/08 Zhao_Jie 作成 Copyright (c) 2014 MicroAd,Inc. All Rights Reserved.
 */

package hungry.common;

import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

@Component
public class SspMailSender {

    // メールテンプレートのパス
    private String templatePath;

    // テンプレートのパラメータ
    private Map<String, Object> model;

    // メールの宛先
    private String recipient;

    // メールの内容
    private String text;

    // 主題

    @Value(value = "${mail.from.address}")
    private String fromAddress;

    @Value(value = "${mail.from.password}")
    private String password;

    @Value(value = "${mail.smtp.host}")
    private String smtpHost;

    @Value(value = "${mail.smtp.port}")
    private String smtpPort;

    @Value(value = "${mail.smtp.auth}")
    private String smtpAuth;

    /**
     * メールを送る。
     */
    public void sendEmail(String subject) {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        // SMTP認証
        Properties prop = new Properties();

        if (StringUtils.isNotEmpty(smtpAuth)) {
            prop.put("mail.smtp.auth", smtpAuth);
        }
        mailSender.setJavaMailProperties(prop);

        mailSender.setHost(smtpHost);
        mailSender.setPort(Integer.valueOf(smtpPort));
        mailSender.setUsername(fromAddress);

        if (StringUtils.isNotEmpty(password)) {
            mailSender.setPassword(password);
        }

        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setFrom(mailSender.getUsername());
        smm.setTo(recipient);
        smm.setSubject(subject);

        // メールの内容を取得する
        String mailText = getMailText();
        smm.setText(mailText);

        mailSender.send(smm);
    }

    /**
     * メールの内容を取得する。
     * @return メールの内容
     */
    private String getMailText() {
        String mailText = null;

        // メールのテンプレート取得
        if (StringUtils.isNotEmpty(templatePath)) {
            VelocityEngine velocityEngine = new VelocityEngine();
            Properties p = new Properties();
            p.setProperty("resource.loader", "class");
            p.setProperty("class.resource.loader.class",
                    "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            velocityEngine.init(p);

            try {
                mailText = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templatePath, "iso-2022-jp",
                        model);
            } catch (Exception e) {
                throw new RuntimeException("メールのテンプレートファイル取得できなかった");
            }

            return mailText;
        }

        mailText = text;

        return mailText;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
