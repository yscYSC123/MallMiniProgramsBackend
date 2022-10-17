package com.javaclimb.entity;

import javax.persistence.*;

/**
 *   购物车信息表
 */
@Table(name = "cart_info")
public class CartInfo {
    /**
     *   主键
     */
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    /**
     *   所属商品ID
     */
    @Column(name = "goodsId")
    private Long goodsid;

    /**
     *   数量
     */
    @Column(name = "count")
    private Integer count;

    /**
     *   所属用户ID
     */
    @Column(name = "userId")
    private Long userid;

    /**
     *   用户等级
     */
    @Column(name = "level")
    private Integer level;

    /**
     *   创建时间
     */
    @Column(name = "createTime")
    private String createtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cart_info.id
     *
     * @return the value of cart_info.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cart_info.id
     *
     * @param id the value for cart_info.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cart_info.goodsId
     *
     * @return the value of cart_info.goodsId
     *
     * @mbg.generated
     */
    public Long getGoodsid() {
        return goodsid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cart_info.goodsId
     *
     * @param goodsid the value for cart_info.goodsId
     *
     * @mbg.generated
     */
    public void setGoodsid(Long goodsid) {
        this.goodsid = goodsid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cart_info.count
     *
     * @return the value of cart_info.count
     *
     * @mbg.generated
     */
    public Integer getCount() {
        return count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cart_info.count
     *
     * @param count the value for cart_info.count
     *
     * @mbg.generated
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cart_info.userId
     *
     * @return the value of cart_info.userId
     *
     * @mbg.generated
     */
    public Long getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cart_info.userId
     *
     * @param userid the value for cart_info.userId
     *
     * @mbg.generated
     */
    public void setUserid(Long userid) {
        this.userid = userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cart_info.level
     *
     * @return the value of cart_info.level
     *
     * @mbg.generated
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cart_info.level
     *
     * @param level the value for cart_info.level
     *
     * @mbg.generated
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cart_info.createTime
     *
     * @return the value of cart_info.createTime
     *
     * @mbg.generated
     */
    public String getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cart_info.createTime
     *
     * @param createtime the value for cart_info.createTime
     *
     * @mbg.generated
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }
}