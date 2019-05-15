package com.jxak.education.utils;

/**
 * 系统全局统一状态机制配置
 */
public class EnumState {

    /**
     * 需求变更记录状态
     */
    public enum changeState {
        before(0, "变更前"),
        after(1, "变更后");
        private int state;
        private String remark;
        public int getState() {
            return this.state;
        }
        public String getRemark() {
            return this.remark;
        }
        changeState(int changeState,String stateRemark){
            this.state=changeState;
            this.remark=stateRemark;
        }
    }
}
