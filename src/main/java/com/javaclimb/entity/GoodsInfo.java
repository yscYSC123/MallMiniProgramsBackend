package com.javaclimb.entity;

import com.github.pagehelper.util.StringUtil;

import javax.persistence.*;
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
    @Column(name = "name")
    private String name;

    /**
     *   商品价格
     */
    @Column(name = "price")
    private Double price;

    /**
     *   商品折扣
     */
    @Column(name = "discount")
    private Double discount;

    /**
     *   商品销量
     */
    @Column(name = "sales")
    private Integer sales;

    /**
     *   商品点赞数
     */
    @Column(name = "hot")
    private Integer hot;

    /**
     *   是否推荐
     */
    @Column(name = "recommend")
    private String recommend;

    /**
     *   库存
     */
    @Column(name = "count")
    private Integer count;

    /**
     *   所属类别ID
     */
    @Column(name = "typeid")
    private Long typeid;

    /**
     *   商品图片id，用英文逗号隔开
     */
    @Column(name = "fields")
    private String fields;

    /**
     *   所属卖家ID
     */
    @Column(name = "userid")
    private Long userid;

    /**
     *   所属卖家等级
     */
    @Column(name = "level")
    private Integer level;

    /**
     *   商品描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 所属类别名称
     */
    @Transient
    private String typeName;

    /**
     * 所属卖家姓名
     */
    @Transient
    private String userName;

    /**
     *  商品图片具体地址列表
     */
    @Transient
    private List<Long> fileList;

    /**
     * 每个已购买用户评价状态
     */
    @Transient
    private String commentStatus;

    /**
     *   商品描述
     */
    @Transient
    private String descriptionView;

    public String getDescriptionView() {
        if (StringUtil.isEmpty(description)){
            return "";
        }
        if (description.length()>30){
            return description.substring(0,30)+"...";
        }
        return description;
    }

    public void setDescriptionView(String descriptionView) {
        this.descriptionView = descriptionView;
    }

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

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Long> getFileList() {
        return fileList;
    }

    public void setFileList(List<Long> fileList) {
        this.fileList = fileList;
    }

    public String getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
    }
}