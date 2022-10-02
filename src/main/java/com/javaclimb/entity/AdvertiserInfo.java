package com.javaclimb.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *   公告
 */
@Table(name = "advertiser_info")
public class AdvertiserInfo {
    /**
     *   主键
     */
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    /**
     * Database Column Remarks:
     *   公告标题
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column advertiser_info.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     * Database Column Remarks:
     *   添加时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column advertiser_info.time
     *
     * @mbg.generated
     */
    private String time;

    /**
     * Database Column Remarks:
     *   公告内容
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column advertiser_info.content
     *
     * @mbg.generated
     */
    private String content;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column advertiser_info.id
     *
     * @return the value of advertiser_info.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column advertiser_info.id
     *
     * @param id the value for advertiser_info.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column advertiser_info.name
     *
     * @return the value of advertiser_info.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column advertiser_info.name
     *
     * @param name the value for advertiser_info.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column advertiser_info.time
     *
     * @return the value of advertiser_info.time
     *
     * @mbg.generated
     */
    public String getTime() {
        return time;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column advertiser_info.time
     *
     * @param time the value for advertiser_info.time
     *
     * @mbg.generated
     */
    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column advertiser_info.content
     *
     * @return the value of advertiser_info.content
     *
     * @mbg.generated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column advertiser_info.content
     *
     * @param content the value for advertiser_info.content
     *
     * @mbg.generated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}