package com.example.weibohuati.model;

public class UserWeiboLike {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_weibo_like.id
     *
     * @mbg.generated Tue Apr 30 21:08:34 EDT 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_weibo_like.user_id
     *
     * @mbg.generated Tue Apr 30 21:08:34 EDT 2019
     */
    private Integer user_id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_weibo_like.weibo_id
     *
     * @mbg.generated Tue Apr 30 21:08:34 EDT 2019
     */
    private Integer weibo_id;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_weibo_like.id
     *
     * @return the value of user_weibo_like.id
     *
     * @mbg.generated Tue Apr 30 21:08:34 EDT 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_weibo_like.id
     *
     * @param id the value for user_weibo_like.id
     *
     * @mbg.generated Tue Apr 30 21:08:34 EDT 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_weibo_like.user_id
     *
     * @return the value of user_weibo_like.user_id
     *
     * @mbg.generated Tue Apr 30 21:08:34 EDT 2019
     */
    public Integer getUser_id() {
        return user_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_weibo_like.user_id
     *
     * @param user_id the value for user_weibo_like.user_id
     *
     * @mbg.generated Tue Apr 30 21:08:34 EDT 2019
     */
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_weibo_like.weibo_id
     *
     * @return the value of user_weibo_like.weibo_id
     *
     * @mbg.generated Tue Apr 30 21:08:34 EDT 2019
     */
    public Integer getWeibo_id() {
        return weibo_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_weibo_like.weibo_id
     *
     * @param weibo_id the value for user_weibo_like.weibo_id
     *
     * @mbg.generated Tue Apr 30 21:08:34 EDT 2019
     */
    public void setWeibo_id(Integer weibo_id) {
        this.weibo_id = weibo_id;
    }
}