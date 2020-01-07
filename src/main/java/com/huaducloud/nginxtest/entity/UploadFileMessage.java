package com.huaducloud.nginxtest.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author huanglishun
 * @date 2020-01-07 9:36
 */
@Entity
@Table(name = "hdy_upload_file_message")
@org.hibernate.annotations.Table(appliesTo = "hdy_upload_file_message",comment = "文件上传信息表")
public class UploadFileMessage implements Serializable {


}
