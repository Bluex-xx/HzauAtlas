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
		 	<input v-model:value="value" placeholder="Search cute" placeholder-style="color:rgba(158, 158, 158, 0.46);font-weight:500;">
		 </view>
		 </input>
         <image @click="search()" src="../../static/search-button.png" class="search-button"></image>
		 <image src="../../static/cat-icon.png" class="cat-bg"></image>
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
	 	<view v-for="i in list" class="cat_one">
			<image @click="todetail" :src="i.store" class="cat_one_image" mode="aspectFill"></image>
			<view class="cat_name">
				名字
			</view>
			<image v-show="!i.islike" src="../../static/heart-icon.png"  class="love"></image>
			<image v-show="i.islike" src="../../static/heart-icon-selected.png"  class="love"></image>
	 	</view>
	 </view>
	</view>
</template>
<script>
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
				list:""
			}
		},
		methods: {
        
            todetail(){
				uni.navigateTo({	
					url: '../../packageA/pages/detail/detail'
				})
			},
			toscience(){
				uni.navigateTo({	
					url: '../../packageA/pages/science/science'
				})
			},
			getList()
			{
				api.indexRecommand({uid:1,type:this.tabindex}).then(
				res => {
						this.list = res
						console.log(this.list)
						}).catch(err => {
							console.log(err)
						})
			},
			search(){
				if(this.tabindex==1)
				{
					api.indexCatSearch({uid:1,information:this.value}).then(
					res => {
								if(res.length==0)
								{
									uni.showModal({
										content: '没有搜到噢',
										showCancel: false
									});
								}
								else
								{
									this.list = res
								}
							 }).catch(err => {
							 	console.log(err)
							 })  
					
				}
                else
				{
					api.indexFlowerSearch({uid:1,information:this.value}).then(
					res => {
								if(res.length==0)
								{
									uni.showModal({
										content: '没有搜到噢',
										showCancel: false
									});
								}
								else
								{
									this.list = res
								}
							}).catch(err => {
								console.log(err)
							})
				}
			},
			changeselect(data){
			 	this.tabindex=data;
				this.getList(data);
			}
		},
		 mounted(){
              this.getList();
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
		left:0;
	}
    /* 	tabtitle_1 */
	.tabtitle-1
	{
		position: absolute;
		left: 60rpx;
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
		left: 1rpx;
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
</style>
