<template>
	<view class="content">
		<image @click="backToLast" src="../../../static/back-icon.png" class="back"></image>
		<view class="detail_banner">
			<image :src="list.pictureList[0].store" mode="aspectFill" style="height: 100%;width: 100%;"></image>
		</view>
<!-- 		猫猫信息 -->
        <view class="cat_information_bg">
        	<view class="cat_title">
        		{{title}}
        	</view>
			<view class="title_line"></view>
			<image @click="islike()" v-show="!list.pictureList[0].islike" src="../../../static/heart-icon.png" class="love"></image>
		    <image @click="islike()" v-show="list.pictureList[0].islike" src="../../../static/heart-icon-selected.png" class="love"></image>
			<!-- <image  src="../../../static/share-icon.png" class="share"></image> -->
			<button  class="share" type="default" open-type="share"></button>
			<!-- @click="sharevx(list.pictureList[0].store)" -->
			<view class="main_text">
			<view class="cat_name">
				<image src="../../../static/name-icon.png" class="name_icon"></image>
				<view>
					大名:{{list.name}}
				</view>
			</view>
			<view class="cat_name">
				<image src="../../../static/name-icon.png" class="name_icon"></image>
				<view v-show="tabindex==1">
					性格:{{list.character}}
				</view>
				<view v-show="tabindex==2">
					品种:{{list.department}}
				</view>
			</view>
			<view class="cat_body">
				<image src="../../../static/body-icon.png" class="body_icon"></image>
				<view v-show="tabindex==1">
					颜色:{{list.color}}
				</view>
				<view v-show="tabindex==2">
					花期:{{list.florescence}}
				</view>
			</view>
			<view class="cat_location">
				<image src="../../../static/location-icon.png" class="location_icon"></image>
				<view v-show="tabindex==1">
					出没地点:{{list.place}}
				</view>
				<view v-show="tabindex==2">
					花语:{{list.allegory}}
				</view>
			</view>
			</view>
        </view>
        <!-- 猫猫相册 -->
		<view class="cat_photos">
		<view class="cat_title">
			{{photo_title}}
		</view>
		<view class="title_line" style="width: 130rpx;"></view>	
		<view class="main_photo">
			<view v-for="i in list.pictureList" class="photo_one" @click="previewImg(i.store)">
				<image :src="i.store" mode="aspectFill" style="width: 100%;height: 100%;"></image>
			</view>
		</view>
		</view>
	</view>
</template>

<script>
import api from '@/api/api.js'
export default {
		data() {
			return {
				title:"猫猫信息",
				photo_title:"照片集",
				pid:"",
				list:"",
				tabindex:""
			}
		},
		methods: {
			// 返回上一页面
            backToLast(){
				let pages = getCurrentPages()
				if(pages[pages.length-2]) {
					uni.navigateBack({
						url:pages[pages.length-2].route
					})
				}
				else {
					uni.switchTab({
						url: '../../../pages/index/index'
					})
				}
			},
			//照片集放大预览
			previewImg(data){
			uni.previewImage({
				// 当前需要预览的图片
				current:""+data,
				//所有图片
				urls:[data]
			});			
			},
			//点赞函数
			islike(){
				api.picLike({uid:1,pid:this.list.pid}).then(
				res => {
						if(res=="操作成功")
						{
							this.list.pictureList[0].islike=!this.list.pictureList[0].islike
			                console.log(this.list.pictureList[0].islike);
						}
						}).catch(err => {
							console.log(err)
						})
			}
		},
		onLoad: function (option) {
			const pages = getCurrentPages()
			if(	pages[pages.length-2]) {
				if(pages[pages.length-2].route == 'packageA/pages/list/list') {
					this.tabindex = uni.getStorageSync('categoryindex')
					
				}
				else {
					this.tabindex = uni.getStorageSync('tabindex');
				}
			}
			else {
				this.tabindex = option.tabindex
			}
		        
			if(this.tabindex==1)
			{
				//获取猫咪详情
				api.catDetail({uid:1,pid:option.data}).then(
				res => {
					this.list = res
					}).catch(err => {
						console.log(err)
					})
			}
            else {
				this.title = '花花信息'
				//获取花花详情
				api.flowerDetail({uid:1,pid:option.data}).then(
				res => {
					this.list = res
					}).catch(err => {
						console.log(err)
					})
			}
		},
		// 分享
		onShareAppMessage(res) {
			if(res.from === 'button') {
				console.log(res.target)
			}
			return {
				title: '狮山图tututu鉴',
				path: `/packageA/pages/detail/detail?data=${this.list.pid}&tabindex=${this.tabindex}`,
				imageUrl: this.list.pictureList[0].store,
				// success(res) {
				// 	uni.showToast({
				// 		title:'分享成功'
				// 	})
				// }
			}
		}
	}
</script>

<style scoped>
	.content
	{
		width: 100%;
	}
	/* 详情页banner */
	.detail_banner
	{
		width: 750rpx;
		height: 550rpx;
	}
	/* 猫猫信息 */
	.cat_information_bg
	{

		position: relative;
		left: 42.24rpx;
		top: -100rpx;
		width: 666rpx;
		border-radius: 38.4rpx;
		background: #FFFFFF;
	}
	.cat_title
	{
        position: absolute;
		left: 50rpx;
		top:30rpx;
		width: 128px;
		height: 34px;
		font-size: 24px;
		font-weight: normal;
		letter-spacing: 0em;
		color: #212121;
	}
	.title_line
	{
		position: absolute;
		left: 50rpx;
		top: 100rpx;
		width: 175rpx;
		transform: rotate(180deg);
		border: 1px solid #FAACA8;
	}
	.main_text
	{
		width: 90%;
		padding: 140rpx 0 30rpx 30rpx;
	}
	.love
	{
		position: absolute;
		right: 50rpx;
		top:35rpx;
		width:50rpx;
		height: 50rpx;
	}
	.share
	{
		position: absolute;
		right: 120rpx;
		top:37rpx;
		width:50rpx;
		height: 50rpx;
		background-image: url(../../../static/share-icon.png);
		background-repeat: no-repeat;
		background-color: rgba(74, 255, 204, 0.0);
	}
	button::after {
		border: none;
	}
	.back
	{
		position: fixed;
		top: 95rpx;
		left: 35rpx;
		width: 50rpx;
		height: 50rpx;
		padding: 10rpx;
		border-radius: 20rpx;
		background: rgba(0, 0, 0, 0.3);
		border: solid #C8C7CC 1px;
	}
	.cat_name,.cat_body,.cat_location
	{
		color: #9E9E9E;
		font-size: 30rpx;
		font-weight: 400;
		word-wrap:break-word;
		margin-bottom: 20rpx;
		letter-spacing: 2rpx;
	}
	.name_icon,.body_icon,.location_icon
	{
		height: 45rpx;
		width: 45rpx;
		float: left;
		padding-right:7rpx;
	}
    .cat_photos
	{
		position: relative;
		left: 42.24rpx;
		top: -60rpx; 
		width: 666rpx;
		border-radius: 38.4rpx;
		background: #FFFFFF;
	}
	.main_photo
	{
		display: flex;
		flex-wrap: wrap;
		padding:140rpx 40rpx 50rpx 40rpx;
	}
	.photo_one
	{
		width: 183rpx;
		height: 183rpx;
		margin: 6rpx;
		box-shadow: 0px 4px 10px 0px rgba(0, 0, 0, 0.4);
	}
</style>