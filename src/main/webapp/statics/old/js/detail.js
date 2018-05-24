if(5==$('#productId').val()){
    var data = [
        {
            msg:'API详情',
            show:true
        },
        {
            msg:'详情介绍',
            show:false
        },
        {
            msg:'错误码参照',
            show:false
        },
        {
            msg:'评论',
            show:false
        },
        {
            msg:'下载记录',
            show:false
        }
    ];
}else{
    var data = [
        {
            msg:'API详情',
            show:true
        },
        {
            msg:'详情介绍',
            show:false
        },
        {
            msg:'错误码参照',
            show:false
        },
        {
            msg:'评论',
            show:false
        },
        {
            msg:'订单记录',
            show:false
        }
    ];
}

var vue = new Vue({
    el:'#conBox',
    data:{
        data:data,
        com:[],
        order:[],
        commentCurPage:1,
        totalCommentPage:'',
        orderCurPage:1,
        totalOrderPage:'',
        warnMsg:'',
        commentContent:'',
        lastComment:'',

    },
    methods:{
        isShow:function(index){
            for(i in this.data){
                this.data[i].show = false;
            }
            $('#con_detail').children().hide();
            $('#con_detail').children().eq(index).show();
            var self = this.data[index];
            self.show = true;
        },
        addComPage:function(){
            if(this.commentCurPage >= this.totalCommentPage){
                return false;
            }else{
                this.commentCurPage++;
            }
            this.reqComment();
        },
        reduceComPage:function(){
            if(this.commentCurPage <= 1){
                return false;
            }else{
                this.commentCurPage--;
            }
            this.reqComment();
        },
        addOrderPage:function(){
            if(this.orderCurPage >= this.totalOrderPage){
                return false;
            }else{
                this.orderCurPage++;
            }
            this.reqOrder();
        },
        reduceOrderPage:function(){
            if(this.orderCurPage <= 1){
                return false;
            }else{
                this.orderCurPage--;
            }
            this.reqOrder();
        },
        firstCommentPage:function(){
            this.commentCurPage = 1;
            this.reqComment();

        },
        lastCommentPage:function(){
            this.commentCurPage = this.totalCommentPage;
            this.reqComment();
        },
        firstOrderPage:function(){
            this.orderCurPage = 1;
            this.reqOrder();
        },
        lastOrderPage:function(){
            this.orderCurPage = this.totalOrderPage;
            this.reqOrder();
        },
        reqComment:function(){
            var self = this;
            var data = { productId: $('#productId').val(), curPage : this.commentCurPage };
            $.ajax({
                type:"get",
                url:"/comment/product",
                async:true,
                data:data,
                datatype:'json',
                success:function(res){
                    if(res.success){
                        self.com = res.data.comment;
                        self.commentCurPage = res.data.curPage;
                        self.totalCommentPage = res.data.pageCount;
                    }
                }
            });


        },
        reqOrder:function(){
            var self = this;
            $.ajax({
                type:"get",
                url:"/product/order",
                async:true,
                data:{ productId:$('#productId').val() , curPage : self.orderCurPage },
                datatype:'json',
                success:function(res){
                    if(res.success){
                        self.order = res.data.comment;
                        self.orderCurPage = res.data.curPage;
                    }else{
                        alert(res.msg);
                    }
                }
            });
        },
        addComment:function(){
            if(this.commentContent == ''){
                this.warnMsg = '评论内容不能为空,且不能超过150字!';
                return false;
            }else{
                this.warnMsg = "";
                var self = this;
                $.ajax({
                    type:"get",
                    url:"/comment/add",
                    async:true,
                    data:{ productId:$('#productId').val() , comment : self.commentContent, userId:1},
                    datatype:'json',
                    success:function(res){
                        if(res.success){
                            var data = { productId: $('#productId').val(), curPage : 1 };
                            $.ajax({
                                type:"get",
                                url:"/comment/product",
                                async:true,
                                data:data,
                                datatype:'json',
                                success:function(res){
                                    if(res.success){
                                        self.commentContent = '';
                                        self.com = res.data.comment;
                                        self.commentCurPage = res.data.curPage;
                                        self.totalCommentPage = res.data.pageCount;
                                    }
                                }
                            });
                        }else{
                            alert(res.msg);
                        }
                    }
                });
            }

        }
    }

});
$.ajax({
    type:"get",
    url:"/comment/product",
    async:true,
    data:{ productId:$('#productId').val() , curPage : vue.commentCurPage},
    datatype:'json',
    success:function(res){
        if(res.success){
            vue.com = res.data.comment;
            vue.commentCurPage = res.data.curPage;
            vue.totalCommentPage = res.data.pageCount;
        }
    }
});
$.ajax({
    type:"get",
    url:"/product/order",
    async:true,
    data:{ productId:$('#productId').val() , curPage : vue.orderCurPage },
    datatype:'json',
    success:function(res){
        if(res.success){
            vue.order = res.data.comment;
            vue.orderCurPage = res.data.curPage;
            vue.totalOrderPage = res.data.pageCount;

        }
    }
});
var priceVue = new Vue({
    el:'.info-show',
    data:{
        data:[

        ],
        acc:[

        ],
        price:[

        ],
        count:1,
        packagetype:'',
        currentAmount:"",
        currentAcc:1,
        returnData:[],
        pricePackage:'',
        none:false,
        tipshow:true,
        allow:false
    },
    methods:{
        select:function(index){
            for(i in this.data){
                this.data[i].bor = false;
            }
            this.data[index].bor = true;
            this.currentAmount = this.data[index].amountStr;
        },
        selectPackagetype:function(index){
            for(i in this.acc){
                this.acc[i].sel = false;
            }
            this.acc[index].sel = true;
            this.currentAcc = this.acc[index].id;
        },
        takeData:function(){
            this.returnData = [];
            this.returnData.count = this.count;
            this.returnData.acc = this.currentAcc;
            this.returnData.amount = this.currentAmount;
            return this.returnData;
        },
        add:function(){
            if(this.count >= 1000){
                return false;
            }else{
                this.count++;
            }
        },
        reduce:function(){
            if(this.count <= 1){
                return false;
            }else{
                this.count--;
            }
        },
        reqPackagetype:function(){

            var self = this;
            $.ajax({
                type:"get",
                url:"/product/packagetype",
                async:true,
                data:{ productId : $('#productId').val() },
                datatype:'json',
                success:function(res){
                    if(res.success === true){
                        res.data[0].sel = true;
                        for(var i = 1; i < res.data.length; i++){
                            res.data[i].sel = false;
                        }
                        self.acc = res.data;
                    }else{
                        //alert(res.msg);
                    }

                }
            });
        },
        reqAmount:function(){
            var self = this;
            $.ajax({
                type:"get",
                url:"/product/amount",
                async:true,
                data:{ productId :$('#productId').val() , accuracy : self.currentAcc},
                datatype:'json',
                success:function(res){
                    if(res.success === true){
                        res.data[0].bor = true;
                        for(var i = 1; i < res.data.length; i++){
                            res.data[i].bor = false;
                        }
                        self.data = res.data;
                    }else{
                        alert(res.msg);
                    }
                }
            });
        },
        reqPrice:function(){
            for(i in this.data){
                if(this.data[i].bor == true){
                    num = this.data[i].amount;
                }
            }
            for(i in this.acc){
                if(this.acc[i].bor == true){
                    par = this.acc[i].accuracy;
                }
            }
            var self = this;
            $.ajax({
                type:"get",
                url:"/product/price",
                async:true,
                data:{ productId :$('#productId').val() , accuracy : self.currentAcc, amountStr: self.currentAmount},
                datatype:'json',
                success:function(res){
                    self.price = res.data;
                    self.pricePackage = res.data.id;
                }
            });

        }
    }
});
priceVue.reqPackagetype();
$.ajax({
    type:"get",
    url:"/product/amount",
    async:true,
    data:{ productId : $('#productId').val() , accuracy : priceVue.currentAcc},
    datatype:'json',
    success:function(res){
        if(res.success === true){
            if(res.data.length <= 0){
                priceVue.none = true;
            }else{
                res.data[0].bor = true;
                for(var i = 1; i < res.data.length; i++){
                    res.data[i].bor = false;
                }
                priceVue.data = res.data;
            }

        }else{
            alert(res.msg);
        }
    }
});
$.ajax({
    type:"get",
    url:"/product/price",
    async:true,
    data:{ productId : $('#productId').val() , accuracy : priceVue.currentAcc, amountStr: priceVue.currentAmount},
    datatype:'json',
    success:function(res){
        if(res.msg == '查询套餐结果为空'){
            priceVue.price = res.data;
            priceVue.none = true;
            if(res.data.total == "即将上线"){
                priceVue.tipshow = false;
            }else{
                priceVue.tipshow = true;
            }
        }else{
            priceVue.price = res.data;
            //console.log(res.data)
            priceVue.pricePackage = res.data.id;
            priceVue.none = false;
        }
    }
});
$(function(){
    if($('#productName').val() == 'IP区县库'){
        priceVue.allow = true;
        vue.isShow(1);
    }
});
Date.prototype.toLocaleString = function() {
    if(this.getMinutes() <= 9){
        return this.getFullYear() + "-" + (this.getMonth() + 1) + "-" + this.getDate() + "   " + this.getHours() + ":0" + this.getMinutes();
    }else{
        return this.getFullYear() + "-" + (this.getMonth() + 1) + "-" + this.getDate() + "   " + this.getHours() + ":" + this.getMinutes();
    }

};
