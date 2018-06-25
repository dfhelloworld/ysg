<template>
    <div class="user_center">
        <h2>{{fullName}}</h2>
        <!--<p>View profile</p>-->
        <ul>
            <li v-if="idType == 1">
                <div class="left">{{language.myCenter.room_info}}</div>
                <div class="right">No.{{roomInfo}}</div>
            </li>
            <!--<li v-if="idType == 2">-->
                <!--<div class="left">{{language.myCenter.oaId}}</div>-->
                <!--<div class="right">No.{{oa_id}}</div>-->
            <!--</li>-->
            <router-link to="/home/news">
                <li>
                    <div class="left">{{language.myCenter.message}}</div>
                    <div class="right"><i class="message"></i></div>
                </li>
            </router-link>
            <!-- <router-link to="/order">
                <li v-show="idType == 2">
                    <div class="left">{{language.myCenter.order}}</div>
                    <div class="right"><i class="notice"></i></div>
                </li>
            </router-link> -->
            <router-link to="/viewOrder">
                <li>
                    <div class="left">{{language.myCenter.viewOrder}}</div>
                    <div class="right"><i class="viewOrder"></i></div>
                </li>
            </router-link>
            <li v-show="idType == 2" @click="goGsm">
                <div class="left">{{language.myCenter.gsm}}</div>
                <div class="right"><i class="message"></i></div>
            </li>
            <li @click="goBill" v-show="idType == 1">
                <div class="left">{{language.myCenter.bill}}</div>
                <div class="right"><i class="message"></i></div>
            </li>
            <!--<li @click="goInvoices" v-show="idType == 1">-->
                <!--<div class="left">{{language.myCenter.invoices}}</div>-->
                <!--<div class="right"><i class="message"></i></div>-->
            <!--</li>-->
            <li @click="changePwd">
                <div class="left">{{language.myCenter.changePwd}}</div>
                <div class="right"><i class="loginout"></i></div>
            </li>
            <li @click="logOut">
                <div class="left">{{language.myCenter.log_out}}</div>
                <div class="right"><i class="loginout"></i></div>
            </li>
        </ul>
    </div>
</template>

<style>
    .user_center{position:absolute;top:0;left:0;width:100%;height:100%;padding: 1rem .36rem;background: #fff;z-index: 1;}
    .user_center h2{color: #4a4a4a;font-size: .48rem;}
    .user_center p{font-size: .32rem;margin-top: .2rem;color: #666;}
    .user_center>ul{margin-top: 1.2rem;}
    .user_center>ul li{height: 1rem;line-height: 1rem;border-bottom: 1px solid #eee;font-size: .28rem;color: #404040;width: 60%;overflow: hidden;padding-right: .3rem;}
    .user_center>ul li .left{float: left;}
    .user_center>ul li .right{float: right;color: #f0c366;}
    .user_center>ul li .right i{float: right;width: .45rem;height: .45rem;margin-top: .25rem;}
    .user_center .message{background: url("../../../assets/images/icon_message.png")center no-repeat;background-size: .3rem;}
    .user_center .notice{background: url("../../../assets/images/icon_notice.png")center no-repeat;background-size: .3rem;}
    .user_center .loginout{background: url("../../../assets/images/icon_loginout.png")center no-repeat;background-size: .3rem;}
    .user_center .viewOrder{background: url("../../../assets/images/viewOrder.png")center no-repeat;background-size: .3rem;}
</style>

<script>
	import { mapState } from 'vuex'
	import { mapGetters } from 'vuex'
	export default {
		data() {
			return {
				fullName:'',
                roomInfo:'',
				idType:'',
                oa_id:''
		    };
		},
        created:function () {
	        //获取用户名称
	        this.fullName = localStorage.FULLNAME;
	        //获取房间号
	        this.roomInfo = localStorage.ROOM_INFO;
	        //获取身份标识
	        this.idType = localStorage.idType;
	        //获取员工id
	        this.oa_id = localStorage.oa_id;
        },
		methods: {
			logOut:function () {
				//清除本地token及fullname
				this.$dialog.confirm({
					title: ' ',
					mes: this.language.common.loginTxt,
					opts: [
						{
							txt: this.language.common.cancel,
							color: false
						},
						{
							txt: this.language.common.sure,
							color: '#f0c366',
							callback: () => {

                                let obj={
                                    userType:'staff',
                                    userId:localStorage.userId,
                                    hotelId: [
                                        'hotel_'+localStorage.HOTELID,
                                        'group_1',
                                        localStorage.LANGUAGE
                                    ]
                                }

                                if(/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)){
                                    removeAllTagAlias(obj.userType,obj.userId,obj.hotelId)
                                } else {
                                    removeMessageTag(obj.userType,obj.userId,obj.hotelId)
                                }

                                localStorage.TOKEN = '';
                                localStorage.FULLNAME = '';
                                localStorage.HOTELID = '';
                                localStorage.idType = '';
                                localStorage.searchFlag = '';
                                this.$router.push('/unlogin');

							}
						}
					]
				});
			},
			goGsm:function () {
				let _this = this;
				let params = {
					token:localStorage.TOKEN
				}
				this.$store.dispatch('getUsers', params).then(function (res) {
					_this.gsmSrc =  res.data.serviceUrl
                    if(/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)){
                        openFile(res.data.serviceUrl)
                    } else {
                        openUrl(res.data.serviceUrl,'gsm')
                    }
				})
//				this.$router.replace({path:'/gsm'})
			},
			goBill:function () {
				this.$router.push({path:'/mybill'})
			},
            goInvoices:function () {
                this.$router.push({path:'/invoices'})
            },
            changePwd: function() {
                let _this = this;
                let alobj = new alertLanguage();
                let obj = alobj.getAlertMsg(localStorage.LANGUAGE);
                let sureBnt = obj.sureBnt;
                let cancelBnt = obj.cancelBnt;
                let dialog = window.YDUI.dialog;
                let pwdHtml = `
                旧密码:<input id="pwdNum1" type="number" oninput="if(value.length>6)value=value.slice(0,6)" style="width:80%;">
                新密码:<input id="pwdNum2" type="number" oninput="if(value.length>6)value=value.slice(0,6)" style="width:80%;">
                `;
                let title = '请输入密码';
                let msg1 = "旧密码为6位数字!";
                let msg2 = "新密码为6位数字!";
                dialog.confirm(title,pwdHtml, [
                    {
                        txt: sureBnt,
                        color: false,
                        stay: true, //是否保留提示框
                        callback: function () {
                            let pwd1 = $("#pwdNum1").val();
                            let pwd2 = $("#pwdNum2").val();
                            if(!/^[0-9]{6}$/.test(pwd1)){
                                alert(msg1);
                                $("#pwdNum1").val("");
                                $("#pwdNum1").focus();
                                return;
                            }
                            if(!/^[0-9]{6}$/.test(pwd2)){
                                alert(msg2);
                                $("#pwdNum2").val("");
                                $("#pwdNum2").focus();
                                return;
                            }
                            dialog.loading.open('Loading');
                            //检查密码
                            let pinParams = {
                                token: localStorage.TOKEN,
                                pin: pwd1
                            };
                            _this.$store.dispatch('checkPin', pinParams).then(function (res) {
                                if(res.code == 0){
                                    //设置密码
                                    let setParams = {
                                        token: localStorage.TOKEN,
                                        pin: pwd2
                                    };
                                    _this.$store.dispatch('setPin', setParams).then(function (res) {
                                        if(res.code == 0){
                                            alert("设置成功!");
                                        }else if(res.code == 1){
                                            alert("token过期，请重新登录!");
                                        }else{
                                            alert(res.msg);
                                        }
                                        dialog.loading.close();
                                        $("#pwdNum1").val("");
                                        $("#pwdNum2").val("");
                                        $("#YDUI_CONFRIM").hide();
                                    });
                                }else if(res.code == 1){
                                    alert("token过期，请重新登录!");
                                    dialog.loading.close();
                                    $("#pwdNum1").val("");
                                    $("#pwdNum2").val("");
                                    $("#YDUI_CONFRIM").hide();
                                }else if(res.code == 2){
                                    alert("旧密码输入错误!");
                                    dialog.loading.close();
                                    $("#pwdNum1").focus();
                                }else{
                                    alert(res.msg);
                                    dialog.loading.close();
                                    $("#pwdNum1").focus();
                                }
                            });
                        }
                    },
                    {
                        txt: cancelBnt,
                        color: false,
                        callback: function () {
                            $("#pwdNum1").val("");
                            $("#pwdNum2").val("");
                        }
                    }
                ]);
                $("#pwdNum1").focus();
            }
		},
		computed: {
			...mapState({
				language: state => state.language.language
			})
		},

	};
</script>
