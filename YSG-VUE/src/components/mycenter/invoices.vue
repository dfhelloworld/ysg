<template>
    <div class="invoice">
        <div class="nav_mark"></div> 
         <yd-navbar title="INVOICES" fixed>
           <span slot="left" class="back" @click="goBack"></span>
        </yd-navbar> 
        <div class="invoice-content">
            <div @click="showHeader"><button type="button">发票抬头</button></div>
            <!-- <div><router-link to="/invoicesManage"><button type="button">发票管理</button></router-link></div>  -->
            <div @click="showManager"><button type="button">发票管理</button></div>
        </div>
    </div>
</template>
<style>
</style>

<script>
	import { mapGetters } from 'vuex'
	import { mapState } from 'vuex'
	export default {
		data() {
			return {
                tokenSrc:''
			};
		},
		created:function () {
            
		},
        mounted:function () {
            //一级页面falg
            isHomePage(0)
        },
        methods: {
            showHeader(){
                let _this = this
                // 获取token
                var dataStr = JSON.stringify({"source":"YSG","otId":"1947364"});
                dataStr = 'dataMap=' + dataStr;
                $.ajax({
                    type:'post',
                    url:'https://bird.ioliu.cn/v1/?url=http://www.xfplink.cn/issp/ApiService?method=xforceplus.sys.qToken',
                    async:false,
                    data:dataStr,
                    dataType:'jsonp',
                    success:function(json){
                        console.log('data—>',json.token);
                        _this.tokenSrc='http://www.xfplink.cn/wx_invoiceTitle_confirm.html?u='+json.token+'&m=YSG&b=X80';
                        //获取发票抬头
                        if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
                            openFile(_this.tokenSrc)
                        }else{
                            openUrl(_this.tokenSrc,"INVOICES")
                        }

                    },
                    error:function () {
                    }
                });

            },



            showManager(){
                let _this = this
                // 获取token
                var dataStr = JSON.stringify({"source":"YSG","otId":"1947364"});
                dataStr = 'dataMap=' + dataStr;
                $.ajax({
                    type:'post',
                    url:'https://bird.ioliu.cn/v1/?url=http://www.xfplink.cn/issp/ApiService?method=xforceplus.sys.qToken',
                    async:false,
                    data:dataStr,
                    dataType:'jsonp',
                    success:function(json){
                        console.log('data—>',json.token);
                        _this.tokenSrc='http://www.xfplink.cn/wx_invoiceCollector.html?u='+json.token+'&m=YSG&b=X80';
                        //获取发票抬头
                        if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
                            openFile(_this.tokenSrc)
                        }else{
                            openUrl(_this.tokenSrc,"INVOICES")
                        }

                    },
                    error:function () {
                    }
                });
            }, 
            goBack(){
                this.$router.go(-1);
            }

        }
	};
</script>
