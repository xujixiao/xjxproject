package com.base.lib.bean;

/**
 * Created by kec on 2017/8/3.
 */

public class LoginBean {


    /**
     * currentPage :
     * data : {"accountName":"五天哦了旅游呀我","accountSource":"中国建设银行","address":"","appUserType":1,"appUserTypeName":"公司","bankAccount":"9555 8895 5899 5666 656","cities":"350,59,102,153,213,263,293,301,329,335,337,346,76,99,138,154,","citiesName":"[{\"cityName\":\"安阳市\",\"id\":59,\"provinceName\":\"河南省\"},{\"cityName\":\"保定市\",\"id\":76,\"provinceName\":\"河北省\"},{\"cityName\":\"宝鸡市\",\"id\":99,\"provinceName\":\"陕西省\"},{\"cityName\":\"安康市\",\"id\":102,\"provinceName\":\"陕西省\"},{\"cityName\":\"保山市\",\"id\":138,\"provinceName\":\"云南省\"},{\"cityName\":\"阿坝州\",\"id\":153,\"provinceName\":\"四川省\"},{\"cityName\":\"巴中市\",\"id\":154,\"provinceName\":\"四川省\"},{\"cityName\":\"安定县\",\"id\":213,\"provinceName\":\"海南省\"},{\"cityName\":\"鞍山市\",\"id\":263,\"provinceName\":\"辽宁省\"},{\"cityName\":\"阿拉善盟\",\"id\":293,\"provinceName\":\"内蒙古自治区\"},{\"cityName\":\"安顺市\",\"id\":301,\"provinceName\":\"贵州省\"},{\"cityName\":\"阿克苏地区\",\"id\":329,\"provinceName\":\"新疆维吾尔自治区\"},{\"cityName\":\"阿勒泰州\",\"id\":335,\"provinceName\":\"新疆维吾尔自治区\"},{\"cityName\":\"阿拉尔市\",\"id\":337,\"provinceName\":\"新疆维吾尔自治区\"},{\"cityName\":\"阿里地区\",\"id\":346,\"provinceName\":\"西藏区\"},{\"cityName\":\"长春市\",\"id\":350,\"provinceName\":\"吉林省\"}]","city":2,"cityName":"北京","companyAddress":"天咯做最在真转","companyName":"旅途T恤","companyPerson":"来咯一www","companyTelephone":"858999","contact":"","country":1,"countryName":"中国","createTime":"2017-06-29 16:36:17","createUser":"523cd76c-28a5-4004-afd4-48b314435ea4","id":"523cd76c-28a5-4004-afd4-48b314435ea4","imageUrl":"/upload/20170703/Luban_1499062666332_20170703021730.jpg","img":"/upload/20170629/mmexport1498623943090_20170629043617.jpg","isBan":0,"isReceiveMessage":1,"isVerify":1,"lawyerPerson":"","password":"96e79218965eb72c92a549dd5a330112","qq":"","regId":"120c83f76012ba2a3a2","telephone":"15012345678","updateTime":"","updateUser":"","userFlag":1,"userFlagName":"卖家","userId":"BJ35MH0","userName":"","weixin":""}
     * error :
     * msg : 操作成功
     * pageCount :
     * pageData :
     * pageSize :
     * recordsTotal :
     * status : 1
     */

    private String currentPage;
    private DataBean data;
    private String error;
    private String msg;
    private String pageCount;
    private String pageData;
    private String pageSize;
    private String recordsTotal;
    private String status;

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public String getPageData() {
        return pageData;
    }

    public void setPageData(String pageData) {
        this.pageData = pageData;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(String recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * accountName : 五天哦了旅游呀我
         * accountSource : 中国建设银行
         * address :
         * appUserType : 1
         * appUserTypeName : 公司
         * bankAccount : 9555 8895 5899 5666 656
         * cities : 350,59,102,153,213,263,293,301,329,335,337,346,76,99,138,154,
         * citiesName : [{"cityName":"安阳市","id":59,"provinceName":"河南省"},{"cityName":"保定市","id":76,"provinceName":"河北省"},{"cityName":"宝鸡市","id":99,"provinceName":"陕西省"},{"cityName":"安康市","id":102,"provinceName":"陕西省"},{"cityName":"保山市","id":138,"provinceName":"云南省"},{"cityName":"阿坝州","id":153,"provinceName":"四川省"},{"cityName":"巴中市","id":154,"provinceName":"四川省"},{"cityName":"安定县","id":213,"provinceName":"海南省"},{"cityName":"鞍山市","id":263,"provinceName":"辽宁省"},{"cityName":"阿拉善盟","id":293,"provinceName":"内蒙古自治区"},{"cityName":"安顺市","id":301,"provinceName":"贵州省"},{"cityName":"阿克苏地区","id":329,"provinceName":"新疆维吾尔自治区"},{"cityName":"阿勒泰州","id":335,"provinceName":"新疆维吾尔自治区"},{"cityName":"阿拉尔市","id":337,"provinceName":"新疆维吾尔自治区"},{"cityName":"阿里地区","id":346,"provinceName":"西藏区"},{"cityName":"长春市","id":350,"provinceName":"吉林省"}]
         * city : 2
         * cityName : 北京
         * companyAddress : 天咯做最在真转
         * companyName : 旅途T恤
         * companyPerson : 来咯一www
         * companyTelephone : 858999
         * contact :
         * country : 1
         * countryName : 中国
         * createTime : 2017-06-29 16:36:17
         * createUser : 523cd76c-28a5-4004-afd4-48b314435ea4
         * id : 523cd76c-28a5-4004-afd4-48b314435ea4
         * imageUrl : /upload/20170703/Luban_1499062666332_20170703021730.jpg
         * img : /upload/20170629/mmexport1498623943090_20170629043617.jpg
         * isBan : 0
         * isReceiveMessage : 1
         * isVerify : 1
         * lawyerPerson :
         * password : 96e79218965eb72c92a549dd5a330112
         * qq :
         * regId : 120c83f76012ba2a3a2
         * telephone : 15012345678
         * updateTime :
         * updateUser :
         * userFlag : 1
         * userFlagName : 卖家
         * userId : BJ35MH0
         * userName :
         * weixin :
         */

        private String accountName;
        private String accountSource;
        private String address;
        private String appUserType;
        private String appUserTypeName;
        private String bankAccount;
        private String cities;
        private String citiesName;
        private String city;
        private String cityName;
        private String companyAddress;
        private String companyName;
        private String companyPerson;
        private String companyTelephone;
        private String contact;
        private String country;
        private String countryName;
        private String createTime;
        private String createUser;
        private String id;
        private String imageUrl;
        private String img;
        private String isBan;
        private String isReceiveMessage;
        private String isVerify;
        private String lawyerPerson;
        private String password;
        private String qq;
        private String regId;
        private String telephone;
        private String updateTime;
        private String updateUser;
        private String userFlag;
        private String userFlagName;
        private String userId;
        private String userName;
        private String weixin;

        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }

        public String getAccountSource() {
            return accountSource;
        }

        public void setAccountSource(String accountSource) {
            this.accountSource = accountSource;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAppUserType() {
            return appUserType;
        }

        public void setAppUserType(String appUserType) {
            this.appUserType = appUserType;
        }

        public String getAppUserTypeName() {
            return appUserTypeName;
        }

        public void setAppUserTypeName(String appUserTypeName) {
            this.appUserTypeName = appUserTypeName;
        }

        public String getBankAccount() {
            return bankAccount;
        }

        public void setBankAccount(String bankAccount) {
            this.bankAccount = bankAccount;
        }

        public String getCities() {
            return cities;
        }

        public void setCities(String cities) {
            this.cities = cities;
        }

        public String getCitiesName() {
            return citiesName;
        }

        public void setCitiesName(String citiesName) {
            this.citiesName = citiesName;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getCompanyAddress() {
            return companyAddress;
        }

        public void setCompanyAddress(String companyAddress) {
            this.companyAddress = companyAddress;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getCompanyPerson() {
            return companyPerson;
        }

        public void setCompanyPerson(String companyPerson) {
            this.companyPerson = companyPerson;
        }

        public String getCompanyTelephone() {
            return companyTelephone;
        }

        public void setCompanyTelephone(String companyTelephone) {
            this.companyTelephone = companyTelephone;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCountryName() {
            return countryName;
        }

        public void setCountryName(String countryName) {
            this.countryName = countryName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateUser() {
            return createUser;
        }

        public void setCreateUser(String createUser) {
            this.createUser = createUser;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getIsBan() {
            return isBan;
        }

        public void setIsBan(String isBan) {
            this.isBan = isBan;
        }

        public String getIsReceiveMessage() {
            return isReceiveMessage;
        }

        public void setIsReceiveMessage(String isReceiveMessage) {
            this.isReceiveMessage = isReceiveMessage;
        }

        public String getIsVerify() {
            return isVerify;
        }

        public void setIsVerify(String isVerify) {
            this.isVerify = isVerify;
        }

        public String getLawyerPerson() {
            return lawyerPerson;
        }

        public void setLawyerPerson(String lawyerPerson) {
            this.lawyerPerson = lawyerPerson;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getRegId() {
            return regId;
        }

        public void setRegId(String regId) {
            this.regId = regId;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getUpdateUser() {
            return updateUser;
        }

        public void setUpdateUser(String updateUser) {
            this.updateUser = updateUser;
        }

        public String getUserFlag() {
            return userFlag;
        }

        public void setUserFlag(String userFlag) {
            this.userFlag = userFlag;
        }

        public String getUserFlagName() {
            return userFlagName;
        }

        public void setUserFlagName(String userFlagName) {
            this.userFlagName = userFlagName;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getWeixin() {
            return weixin;
        }

        public void setWeixin(String weixin) {
            this.weixin = weixin;
        }
    }
}
