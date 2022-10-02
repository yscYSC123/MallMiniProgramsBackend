package com.javaclimb.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 *   商品详情表
 */
@Table(name = "goods_info")
public class GoodsInfo {
    /**
     *   主键
     */
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    /**
     *   商品名称
     */
    private String name;

    /**
     *   商品价格
     */
    private Double price;

    /**
     *   商品折扣
     */
    private Double discount;

    /**
     *   商品销量
     */
    private Integer sales;

    /**
     *   商品点赞数
     */
    private Integer hot;

    /**
     *   是否推荐
     */
    private String recommend;

    /**
     *   库存
     */
    private Integer count;

    /**
     *   所属类别ID
     */
    private Long typeid;

    /**
     *   商品图片id，用英文逗号隔开
     */
    private String fileds;

    /**
     *   所属卖家ID
     */
    private Long userid;

    /**
     *   所属卖家等级
     */
    private Integer level;

    /**
     *   商品描述
     */
    private String description;

    /**
     * 所属类别名称
     */
    private String typeName;

    /**
     * 所属卖家姓名
     */
    private String userName;

    /**
     *  商品图片具体地址列表
     */
    private List<Long> fileList;

    /**
     * 每个已购买用户评价状态
     */
    private String commentStatus;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend == null ? null : recommend.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getTypeid() {
        return typeid;
    }

    public void setTypeid(Long typeid) {
        this.typeid = typeid;
    }

    public String getFileds() {
        return fileds;
    }

    public void setFileds(String fileds) {
        this.fileds = fileds == null ? null : fileds.trim();
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}