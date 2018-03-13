function alertLanguage(){
    this.language ={
        zh : {
        title:'提示信息',
        sureBnt:'确认',
        cancelBnt:'取消',
        shopping:{msg:'是否购买选中的物品？'}
        },
        en : {
            title:'Information',
            sureBnt:'confirm',
            cancelBnt:'cancel',
            shopping:{msg:'Do you confirm the purchase of this item?'}
        }
    };
    this.getAlertMsg = function(localLANGUAGE){
        let obj=this.language.zh;
        if(localLANGUAGE==="en"){
            obj=this.language.en;
        }
        return obj;
    };
}
