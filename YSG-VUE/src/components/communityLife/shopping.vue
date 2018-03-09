<template>
  <div class="search" style="height: 100%">
    <div class="nav_mark"></div>
    <yd-navbar :title="language.community.shopping" fixed>

      <!-- <router-link to="/home" slot="left" v-if="pageFlag == 'home'"> -->
      <span class="close" slot="left" @click="goBack()"></span>
      <!-- </router-link>

      <router-link to="/s_home" slot="left" v-else-if="pageFlag == 's_home'">
          <span class="close"></span>
      </router-link>

      <router-link to="/communityLife" slot="left" v-else>
          <span class="close"></span>
      </router-link> -->


    </yd-navbar>
    <div class="promotiom">
      <section class="promotiom-box">
        <h1>{{language.raiders.title}}</h1>
      </section>
      <section class="promotiom_index raiders">
        <div class="swiper-container">
          <div class="swiper-wrapper ra">
            <div class="swiper-slide" v-for="(item, index) in tagList" @click="changeTab(item.id)">
              <a><span>{{item.title}}</span></a>
            </div>
          </div>
        </div>
      </section>
      <section class="promotiom_list s-list2">
        <scroller  >
          <ul class="type-buy" style="padding-top: 0.5rem">
            <li v-for="data in dataList" @click="goDetail(data.id)">
              <div class="col-4">
                <img  :src="data.pic" alt="">
              </div>
              <div class="col-6">
                <h4>{{data.title}}</h4>
                <p>{{data.introduct}}</p>
                <ul class="s-price">
                  <li class="col-5" style="border:0px">RMB {{data.price}}</li>
                  <li class="col-5" style="border:0px"><button type="button">{{language.community.buy}}</button></li>
                </ul>
              </div>
            </li>
            <li style="border:0px">
              <div style="height:130px;">
                &nbsp;
              </div>
            </li>
          </ul>
          <p class="no_data" v-show="noData">{{language.common.noMoreData}}</p>
        </scroller>
      </section>
    </div>
    <!--<section class="promotiom_list top_nav" style="height: 100%">
      <scroller :on-infinite="infinite">
        <ul class="type-buy" style="padding-top: 0.5rem">
          <li v-for="data in dataList" @click="goDetail(data.id)">
              <div class="col-4">
                  <img  :src="data.pic" alt="">
              </div>
              <div class="col-6">
                  <h4>{{data.title}}</h4>
                  <p>{{data.introduct}}</p>
                  <ul class="s-price">
                    <li class="col-5">RMB {{data.price}}</li>
                    <li class="col-5"><button type="button">{{language.community.buy}}</button></li>
                  </ul>
              </div>
          </li>
        </ul>
        <p class="no_data" v-show="noData">{{language.common.noMoreData}}</p>
      </scroller>
    </section>-->
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
                tagList:[],
                dataList: [],
                nextPage: 1,
                noData: false,
                pageFlag:'',
                preRoute:this.$route.query.info
            }
        },
        created:function () {
            this.pageFlag = this.$route.query.pageFlag;
            //改变滚动条位置
            //setTimeout(function(){
            //$("div[id^='inner']").css({"transform":"translate3d(0,"+translateScrollY.shopping+",0)"});
            //},1500);
        },
        methods: {
            changeTab:function (id) {
                let _this = this
                let params = {
                    hotelid: localStorage.HOTELID,
                    lang: localStorage.LANGUAGE,
                    limit:0,
                    tagid:id,
                    status:1
                }
                //localStorage.LANGUAGE=="en"
                this.$store.dispatch('getShoppingList', params).then(function (res) {
                    _this.dataList=res.data.data.list;
                    console.log(_this.dataList);
                });


            },
            goDetail:function (id) {
                //获取滚动条高度
                //translateScrollY.shopping = culTranslateScrollY($("div[id^='inner']").get(0));
                let data = {};
                for(var key in this.dataList){
                    if (this.dataList[key].id == id){
                        data = this.dataList[key];
                        break;
                    }
                }
                this.$router.push({path:'/buy',query:{info:data}});
            },
            goBack:function(){
                if(this.preRoute){
                    this.$router.push('/'+this.preRoute);
                }else{
                    this.$router.push('/home');
                }

            }
        },
        mounted:function () {
            let _this = this
            let params = {
                hotelid: localStorage.HOTELID,
                lang: localStorage.LANGUAGE,
                limit:0
            }
            this.$store.dispatch('getShoppingTagList', params).then(function (res) {
                _this.tagList = res.data.data.list;
                //初始化tab标签
                $(function() {
                    //初始化tab选择项
                    let slideIndex=translateScrollY.raidersTab;
                    var mySwiper = new Swiper(".raiders .swiper-container", {
                        pagination: ".swiper-pagination",
                        slidesPerView: 3,
                        paginationClickable: true,
                        spaceBetween: 0,
                        initialSlide: slideIndex
                    });
                    //添加标签点击样式
                    $(".ra").on("click", ".swiper-slide", function () {
                        $(this)
                            .addClass("active")
                            .siblings()
                            .removeClass("active");
                    });
                    //tab的click事件触发选择的初始化内容
                    $(".swiper-slide:eq("+slideIndex+")").click();

                });


            });


            //一级页面falg
            isHomePage(0)
        },
        components: {
        },
        computed: {
            ...mapState({
                language: state => state.language.language
    })
    },

    };
</script>


