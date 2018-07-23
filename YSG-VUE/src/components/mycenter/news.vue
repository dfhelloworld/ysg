<template>
    <div class="common_nav_style">
        <div class="nav_mark"></div>
        <yd-navbar :title="language.news.title" fixed>
            <router-link to="/home" slot="left">
                <span class="back"></span>
            </router-link>
        </yd-navbar>
        <div class="news-list">
            <scroller >
                <ul>
                    <li v-for="item in newsList" @click="goDetail(item.url)" v-if="idType==1">
                        <h4>{{item.title}}</h4>
                        <span class="time-show">{{item.createtime}}</span>
                        <p>{{item.value}}</p>
                    </li>
                    <li v-for="item in newsList" @click="goDetail(item.url)" v-if="idType==2">
                        <h4 v-if="isZH">{{item.title_lang1}}</h4>
                        <h4 v-if="!isZH">{{item.title_lang2}}</h4>
                        <span class="time-show">{{item.createtime}}</span>
                        <p v-if="isZH">{{item.value_lang1}}</p>
                        <p v-if="!isZH">{{item.value_lang2}}</p>
                    </li>
                </ul>
            </scroller>
        </div>
    </div>
</template>
<style>
.common_nav_style .m-navbar{z-index: 200}
.news-list ul{padding: .25rem;margin-top: 1.2rem;}
.news-list li{padding: .15rem;border-bottom: 1px solid #ddd;}
.news-list li h4{font-size: .32rem;color: #333;}
.news-list li p{font-size: .28rem;color: #666;display: -webkit-box;-webkit-box-orient: vertical;-webkit-line-clamp: 3;overflow: hidden;}
.time-show{font-size: .22rem;color: #999;}
</style>
<script>
	import { mapGetters } from 'vuex'
	import { mapState } from 'vuex'
	export default {
		data() {
			return {
                newsList:[],
                idType:0,
                isZH:true
			};
		},
        created:function () {
            //判断显示中/英文
            if(localStorage.LANGUAGE!='zh'){
                this.isZH = false;
            }
            let _this = this
	        let params = {
	            hotelid: localStorage.HOTELID,
                lang: localStorage.LANGUAGE,
                page:1,
                limit:5,
                token:localStorage.TOKEN
            }
            //员工消息
            this.idType = localStorage.idType;
            if(2==localStorage.idType){
                _this.$store.dispatch('getStaffAppMsgList', params).then(function (res) {
                    _this.newsList = res.data.list;
                });
            }else{//住户消息
                _this.$store.dispatch('getNews',params).then(function (res) {
                    _this.newsList = res.data.list;
                });
            }

            $(function(){
                $(".navbar-center").css('marginLeft',0);
            });
        },
		methods: {
			goDetail(url){
                // if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
                //     openFile(url)
                // }else{
                //     if(url.indexOf('pdf')>0){
                //         openPdf(url)

                //     } else {
                //         openUrl(url,'')
                //     }
                // }
            }
		},
        mounted:function () {
            //一级页面falg
            isHomePage(0)
        },
        computed: {
            ...mapState({
                language: state => state.language.language
            })

        },
	};
</script>
