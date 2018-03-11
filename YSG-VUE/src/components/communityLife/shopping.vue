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

    <section class="g-flexview">
      <section class="promotiom-box">
        <h1>{{language.raiders.title}}</h1>
      </section>
      <header class="m-navbar">
        <section class="promotiom_index raiders">
          <div class="swiper-container">
            <div class="swiper-wrapper ra">
              <div class="swiper-slide" v-for="(item, index) in tagList" @click="changeTab(item.id)" style="width: 100px">
                <a><span>{{item.title}}</span></a>
              </div>
            </div>
          </div>
        </section>
      </header>
      <section class="g-scrollview">
        <div id="J_ListContent" class="m-list list-theme4">
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
          </ul>
          <p class="no_data" v-show="noData">{{language.common.noMoreData}}</p>
        </div>
      </section>
    </section>
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
                noData: false,
                pageFlag:'',
                preRoute:this.$route.query.info,
                mySwiper:{},
                isInitial:false
            }
        },
        created:function () {
            this.pageFlag = this.$route.query.pageFlag;
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

                this.$store.dispatch('getShoppingList', params).then(function (res) {
                    _this.dataList=res.data.data.list;
                    if(_this.dataList.length==0){
                        _this.noData = true;
                    }else{
                        _this.noData = false;
                    }
                    if(_this.isInitial){
                        //初始化滚动条位置
                        translateScrollY.shopping = 0;
                        setTimeout(function(){
                            $(".g-scrollview").scrollTop(0);
                        },500);
                    }
                });
            },
            goDetail:function (id) {
                //获取滚动条高度
                translateScrollY.shopping = $(".g-scrollview").scrollTop();
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
                    let slideIndex=translateScrollY.shoppingTab;
                    _this.mySwiper = new Swiper(".raiders .swiper-container", {
                        pagination: ".swiper-pagination",
                        slidesPerView: 3,
                        paginationClickable: true,
                        spaceBetween: 0,
                        initialSlide: slideIndex,
                        onTap: function(swiper){
                            translateScrollY.shoppingTab = swiper.clickedIndex;
                        }
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

                    //改变滚动条位置
                    let rollY=translateScrollY.shopping;
                    setTimeout(function(){
                        _this.isInitial = true;
                        $(".g-scrollview").scrollTop(rollY);
                    },1000);

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


