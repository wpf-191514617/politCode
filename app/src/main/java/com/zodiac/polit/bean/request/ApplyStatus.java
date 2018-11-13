package com.zodiac.polit.bean.request;

/**
 * Created by john on 2018/10/10.
 */

public class ApplyStatus {


    /**
     * memberApplay : {"id":"1"}
     */

    private MemberApplayBean memberApplay;

    public MemberApplayBean getMemberApplay() {
        return memberApplay;
    }

    public void setMemberApplay(MemberApplayBean memberApplay) {
        this.memberApplay = memberApplay;
    }

    public static class MemberApplayBean {
        /**
         * id : 1
         */

        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
