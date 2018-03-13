
let zh={
    title:'提示信息',
    sureBnt:'确认',
    cancelBnt:'取消',
    shopping:{msg:'是否购买选中的物品？'}
};
let en={
    title:'Information',
    sureBnt:'confirm',
    cancelBnt:'cancel',
    shopping:{msg:'Do you confirm the purchase of this item?'}
};

function getAlertMsg(localLANGUAGE){
    let obj=zh;
    if(localLANGUAGE=="en"){
        obj=en;
    }
    return obj;
}