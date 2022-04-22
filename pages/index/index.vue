<template>
	<view class="content">
     <view class="index-banner">
		 <view class="index-title-hello">
			 {{title}}
		 </view>
		 <view class="index-title-tip">
		 	   {{tip}}
		 </view>
		 <view class="search">
		 	<input v-model:value="value" placeholder="Search Some" placeholder-style="color:rgba(158, 158, 158, 0.46);font-weight:500;">
		 </view>
		 </input>
         <image @click="throttleSearch" src="../../static/search-button.png" class="search-button"></image>
		 <image v-show="tabindex==1" src="../../static/cat-icon.png" class="cat-bg"></image>
		 <image v-show="tabindex==2" src="../../static/flower-icon-banner.png" class="cat-bg"></image>
		 <view class="tabs">
			 <view @click="changeselect(1)">
			 <image src="../../static/cat-icon-2.png" class="cat-icon"></image>
			 <view class="tabtitle-1">
			 	{{tab_1}}
			 </view>
			 </view>
			 <view v-show="tabindex==1" class="tabline"></view>
			 <image @click="toscience()" src="../../static/science-icon.png" class="science"></image>
			 <view @click="changeselect(2)">
			 <image src="../../static/flower-icon.png" class="flower-icon"></image>
			 <view class="tabtitle-2">
			 	{{tab_2}}
			 </view>
			 </view>
			 <view v-show="tabindex==2" class="tabline-2"></view>
		 </view>
	 </view>
	 <view class="cat_list">
		 <view v-show="loading" class="loading">
             <image src="../../static/loading.gif" class="loading-img"></image>
		 </view>
	 	<view v-if="searchstate==0&&!loading" v-for="(i,index) in list" class="cat_one" :key="index">
			<u--image @click="todetail(i.picture.pid,tabindex)" :src="i.picture.store" class="cat_one_image" mode="aspectFill" :lazy-load="true" width="309.6rpx" height="400rpx">
			  <template v-slot:loading>
			    <u-loading-icon mode="circle" color="red"></u-loading-icon>
			  </template>
			</u--image>		
			<view class="cat_name">
				{{i.name}}
			</view>
			<image @click="islike(i.picture.pid,index)" v-show="!i.picture.islike" src="../../static/heart-icon.png"  class="love"></image>
			<image @click="islike(i.picture.pid,index)" v-show="i.picture.islike" src="../../static/heart-icon-selected.png"  class="love"></image>
	 	</view>
		<view v-if="searchstate==1&&!loading" v-for="(i,index) in list" class="cat_one" :key="index">
			        <u--image  @click="todetail(i.pid,tabindex)" :src="i.store" class="cat_one_image" mode="aspectFill" :lazy-load="true" width="309.6rpx" height="400rpx" >
			          <template v-slot:loading>
			            <u-loading-icon mode="circle" color="red"></u-loading-icon>
			          </template>
			        </u--image>
					<view class="cat_name">
						{{i.name}}
					</view>
					<image @click="islike(i.pid,index)" v-show="!i.islike" src="../../static/heart-icon.png"  class="love"></image>
					<image @click="islike(i.pid,index)" v-show="i.islike" src="../../static/heart-icon-selected.png"  class="love"></image>
			</view>
			<view class="" style="width: inherit;">
				<u-loadmore
					:status="status" 
					:loading-text="loadingText" 
					:loadmore-text="loadmoreText" 
					:nomore-text="nomoreText" 
					marginTop="25"
					loadingIcon="semicircle"
					color="#b7b7b7"
				/>
			</view>
			
	</view>
	
	<image class="scroll_to_top" @click="scrollToTop" src="../../static/scroll_to_top.png" mode="aspectFit"></image>
	
	</view>
	</view>
</template>
<script>
	import { throttle } from '@/util/throttle.js'
	import api from '@/api/api.js'
	export default {
		data() {
			return {
				title:"Hello",
				tip:"There are something cute...",
				value:"",
				tab_1:"猫猫",
				tab_2:"花花",
				tabindex:1,
				searchstate:0,
				listAll:[],
				list:[{
					picture:""
				}],
				cacheList:{
					picture:""
				},
				loading:true,
				status: '',
				loadingText: '努力加载中',
				loadmoreText: '动动手指上拉一下',
				nomoreText: '—— 就到这里喽 ——',
				// page: 1,
				listNum:8,
				uid:1,
				post_state:false
			}
		},
		onReachBottom() {
			this.status = ''
			let addNum = 8
			if(this.listNum >= this.listAll.length) return;
			this.status = 'loading';
			setTimeout(() => {
				for(let i=0; i<addNum; i++) {
					this.list.push(this.listAll[this.listNum+i])
					if(addNum > this.listAll.length-this.listNum) {
						addNum = this.listAll.length-this.listNum
					}
				}
				this.listNum += addNum;
				if(this.listNum >= this.listAll.length) 
				{
					this.status = 'nomore';
				}
				else 
				{
					this.status = 'loading';
				}
			}, 1000)
		},
		methods: {
            //跳转详情页
            todetail(data,tabindex){
				uni.navigateTo({	
					url: `../../packageA/pages/detail/detail?data=${data}&tabindex=${tabindex}`
				})
			},
			toscience(){
				uni.navigateTo({	
					url: '../../packageA/pages/science/science'
				})
			},
			scrollToTop() {
				uni.pageScrollTo({
					scrollTop: 0,
					duration: 300
				});
			},
			//获取首页推荐数据
			getList(){
				this.listNum = 8;
				this.cacheList = [];
				this.loading=true;
				this.post_state=true;
				api.indexRecommand({status:'2',uid:this.uid,type:this.tabindex}).then(
				res => {
					    this.list=[];
					    this.listAll=[]
						this.listAll = res;
						this.post_state=false;
						if(res.length<this.listNum) {
							for(let i=0; i<res.length; i++) {
								this.list.push(res[i])	
							}
						}
						else {
							for(let i=0; i<this.listNum; i++) {
								this.list.push(res[i])	
							}
						}
						this.loading=false;
					})
			},
			//首页搜索接口
			search(){
				this.post_state=true;
				this.status = ''
				if(this.value=="")
				{
					uni.showModal({
						content: '输入为空，请正确输入',
						showCancel: false
					});
					// this.post_state=false;
				}
				else if(this.value.length>20)
				{
					uni.showModal({
						content: '输入语句过长',
						showCancel: false
					});
				}
				else
				{
					this.list=[];
					this.loading=true;
					if(this.tabindex==1)
					{
						//搜索猫猫
						api.indexCatSearch({uid:this.uid,information:this.value}).then(
						res => {
							        this.value='';
									if(res.length==0)
									{
										uni.showModal({
											content: '没有搜到噢',
											showCancel: false
										});
										this.getList();
									}
									else if(res.status==500)
									{
										uni.showModal({
											content: '搜索过于频繁，请一分钟后再试',
											showCancel: false
										});
										this.list = this.cacheList;
										this.loading = false;
									}
									else
									{
										this.listAll = res;
										if(res.length<this.listNum) {
											for(let i=0; i<res.length; i++) {
												this.list.push(res[i])	
											}
										}
										else {
											for(let i=0; i<this.listNum; i++) {
												this.list.push(res[i])	
											}
										}
										this.searchstate=1;
										this.loading=false;
										// this.post_state=false;
									}
								 })		
					}
					else
					{
						//搜索花花
						api.indexFlowerSearch({uid:this.uid,information:this.value}).then(
						res => {
							        this.value='';
									if(res.length==0)
									{
										uni.showModal({
											content: '没有搜到噢',
											showCancel: false
										});
										this.getList();
									}
									else if(res.status==500)
									{
										uni.showModal({
											content: '搜索过于频繁，请一分钟后再试',
											showCancel: false
										});
										this.list = this.cacheList;
										this.loading = false;
									}
									else
									{
										this.listAll = res;
										if(res.length<this.listNum) {
											for(let i=0; i<res.length; i++) {
												this.list.push(res[i])	
											}
										}
										else {
											for(let i=0; i<this.listNum; i++) {
												this.list.push(res[i])	
											}
										}
										this.searchstate=1;
										this.loading=false;
										// this.post_state=false;
									}
								})
							
					}
					this.post_state=false;
				}
				this.post_state=false;
				
			},
			throttleSearch:throttle(function() {
				if(!this.post_state) this.search()
			}),
			//导航切换
			changeselect(data){
				if(!this.post_state)
				{
					if(this.tabindex!=data)
					{
						this.tabindex=data;
						this.searchstate=0;
						this.getList(data);
					}
					else if(this.searchstate==1)
					{
						this.tabindex=data;
						this.searchstate=0;
						this.getList(data);
					}
				}
			},
			//照片点赞
			async islike(data,id){
				if(this.uid=="1")
				{
                    await uni.showModal({
                       	content: '请先登录',
                       	showCancel: false
                       });			
				    uni.switchTab({
						url: '../../pages/mine/mine'
					});
				}
				else
				{
					api.picLike({uid:this.uid,pid:data}).then(
					res => {
							if(res=="操作成功")
							{
								if(this.searchstate==0)
								{
									this.list[id].picture.islike=!this.list[id].picture.islike;
								}
					            else
								{
									this.list[id].islike=!this.list[id].islike;
								}
							}
							}).catch(err => {
								console.log(err)
							})
				}
			}
		},
		onShow(){
		      this.uid=uni.getStorageSync('uid')  ? uni.getStorageSync('uid') : '1';	
		},
		 mounted(){
			  this.uid=uni.getStorageSync('uid') ? uni.getStorageSync('uid') : "1";
              this.getList();
			  this.changeselect(1);
		 }
	}
</script>

<style scoped>
	.content {
	width: 100%;
	}
	
	/* banner */
    .index-banner
	{
		position: absolute;
		left: 0px;
		top: 0px;
		width: 750rpx;
		height: 371.1rpx;
		border-radius: 0px 0px 0px 88rpx;
		background: linear-gradient(115deg, #FFA283 7%, #FFA283 7%, rgba(168,127,125,0.02) 85%, rgba(168,127,125,0.02) 85%);
	}
	
	/* Hello, */
	.index-title-hello
	{
		position: absolute;
		left: 67.3rpx;
		top: 84.5rpx;
		width: 245.8rpx;
		height: 71rpx;
		font-family: BaskOldFace;
		font-size: 69.1rpx;
		font-weight: normal;
		line-height: 76.8rpx;
		letter-spacing: 0em;
		color: rgba(255, 255, 255, 1);
	}
	
	/* Tips */
	.index-title-tip
	{
		position: absolute;
		left: 67.2rpx;
		top: 178.56rpx;
		width: 360.96rpx;
		height: 76.8rpx;
		font-family: MiSans-Bold;
		font-size: 28.8rpx;
		font-weight: bold;
		letter-spacing: 0em;
		color: rgba(255, 255, 255, 0.8);
	}
	/* search */
	.search
	{
		position: absolute;
		left: 97.92rpx;
		top: 301.4rpx;
		width: 458.9rpx;
		height: 36.6rpx;
		border-radius: 19.2rpx;
		background: #FFFFFF;
		box-shadow: 0px 7.68rpx 19.2rpx 0px rgba(0, 0, 0, 0.06);
		padding: 38.4rpx 38.4rpx 38.4rpx 57.6rpx;
		font-family: OPPOSans-M;
		font-size: 34.56rpx;
		font-weight: 500;
		color: rgba(158, 158, 158, 0.46);
	}
	.search input
	{
		width: 360rpx;
	}
	/* search-button */
	.search-button
	{
		position: absolute;
		left: 553rpx;
		top: 333rpx;
		width: 48rpx;
		height: 48rpx;
	}
     /* cat-bg */
	.cat-bg
	{
		margin-left: 200rpx;
		width: 500rpx;
	    height: 400rpx;
	}
	/* tabs */
	.tabs
	{
		position: absolute;
		position: relative;
		top:50rpx;
		left: 120rpx;
		width: 500rpx;
		height: 70rpx;

	}
    /* .cat-icon */
	.cat-icon
	{
		position: absolute;
		width: 55rpx;
		height: 55rpx;
		left:12rpx;
	}
    /* 	tabtitle_1 */
	.tabtitle-1
	{
		position: absolute;
		left: 74rpx;
		top:5rpx;
		color: grey;
	}
	.flower-icon
	{
		position: absolute;
		left: 365rpx;
		width: 45rpx;
		height: 45rpx;
	}
	.tabtitle-2
	{
		position: absolute;
		left: 418rpx;
		top:5rpx;
		color: grey;
	}
	.tabline
	{
		position: absolute;
		width:138rpx;
		height:2rpx;
		left: 13rpx;
		bottom: 0;
		background: #FAACA8;
	}
	.tabline-2
    {
    	position: absolute;
    	width:143rpx;
    	height:2rpx;
    	left: 356rpx;
    	bottom: 0;
    	background: #FAACA8;
    }
	.cat_list
	{
		position: absolute;
		display: flex;
		flex-wrap: wrap;
		width: 680rpx;
		top: 560rpx;
		left: 36rpx;
	}
	.cat_one
	{
		width: 309.6rpx;
		height: 400rpx;
		margin: 16rpx;
		border-radius: 19rpx;
		box-shadow: 0px 4px 10px 0px rgba(0, 0, 0, 0.3);
		overflow: hidden;
		position: relative;
	}
	.cat_one_image
	{
		width: 100%;
		height: 100%;
		object-fit: inherit;
	}
	.cat_name
	{
		border-radius: 19rpx;
		position: absolute;
		left: 20rpx;
		bottom: 20rpx;
		font-size: 25rpx;
		padding: 5rpx 20rpx 5rpx 20rpx;
	    background: rgba(216,216,216,0.63);
		color: #3D3D3C;
	}
	.love
	{
		position: absolute;
		width: 48rpx;
		height: 48rpx;
		right: 20rpx;
		bottom: 20rpx;
	}
	.science
	{
		position: absolute;
		left: 223rpx;
		width: 60rpx;
		height: 60rpx;
	}
	.loading
	{
		position: absolute;
		width: 620rpx;
		left: 0rpx;
		top:30rpx;
		padding: 80rpx 30rpx 100rpx 30rpx;
		background-color: #FFFFFF;
		border-radius: 20rpx;
		z-index: 999;
	}
	.loading-img
	{
		width: 166rpx;
		height: 166rpx;
		margin: 10rpx 0rpx 0 228rpx;
	}
	.scroll_to_top {
		position: fixed;
		bottom: 30rpx;
		right: 30rpx;
		width: 100rpx;
		height: 100rpx;
		border-radius: 50rpx;
		background-color: rgba(212, 212, 212, 0.6);
	}
</style>
