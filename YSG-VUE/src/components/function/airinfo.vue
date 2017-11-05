<template>
    <div class="airinfo">
        <div class="nav_mark"></div>
        <yd-navbar title="AirInfo" fixed>
           <router-link to="/home" slot="left">
                <span class="back" ></span>
            </router-link>
        </yd-navbar>
        <div class="webview">
            <!-- <iframe class="iframe-style" :src="serviceUrl" frameborder="0" scrolling="yes"></iframe> -->
        </div>
    </div>
</template>
<style>
    .iframe-style{width: 100%;height: 100%;padding-top: 1.5rem;}
</style>

<script>
	import { mapGetters } from 'vuex'
	import { mapState } from 'vuex'
	export default {
		data() {
			return {
				serviceUrl:'',
			};
		},
		created:function () { 
            let _this = this;
            let params;
            if(localStorage.TOKEN){
                params = {'token':localStorage.TOKEN,'hotelid':localStorage.HOTELID,lang:localStorage.LANGUAGE};
            } else {
                params = {'hotelid':localStorage.HOTELID,lang:localStorage.LANGUAGE};
            }

            this.$store.dispatch('getAirInfoUrl', params).then(function (res) {
                _this.serviceUrl = res.data.data.serviceUrl;
            });

		},
        mounted:function () {
            //一级页面falg
            isHomePage(0)
        },
	};
</script>
