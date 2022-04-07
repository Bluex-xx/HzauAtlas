<template>
	<view class="content">
		<image @click="backToLast" src="../../../static/back-icon.png" class="back"></image>
		<view class="detail_banner">
			<image src="../../../static/cat_img/4.jpg" mode="aspectFill" style="height: 100%;width: 100%;"></image>
		</view>
<!-- 		猫猫信息 -->
        <view class="cat_information_bg">
        	<view class="cat_title">
        		{{title}}
        	</view>
			<view class="title_line"></view>
		    <image src="../../../static/heart-icon-selected.png" class="love"></image>
			<image src="../../../static/share-icon.png" class="share"></image>
			<view class="main_text">
			<view class="cat_name">
				<image src="../../../static/name-icon.png" class="name_icon"></image>
				<view>
					大名:{{cat_name}}
				</view>
			</view>
			<view class="cat_body">
				<image src="../../../static/body-icon.png" class="body_icon"></image>
				<view>
					体型:{{cat_body}}
				</view>
			</view>
			<view class="cat_location">
				<image src="../../../static/location-icon.png" class="location_icon"></image>
				<view>
					出没地点:{{cat_location}}
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
			<view v-for="i in 8" class="photo_one" @click="previewImg()">
				<image src="../../../static/cat_img/1.jpg" mode="aspectFill" style="width: 100%;height: 100%;"></image>
			</view>
		</view>
		</view>
	</view>
</template>

<script>
export default {
		data() {
			return {
				title:"猫猫信息",
				cat_name:"警长",
				cat_body:"球",
				cat_location:"常常混迹与南湖边,小水沟,以及各类投喂地点,喜欢下午晒太阳",
				photo_title:"照片集"
			}
		},
		methods: {
			// 返回上一页面
            backToLast(){
				let pages = getCurrentPages()
				uni.navigateBack({
					url:pages[pages.length-2].route
				})
			},
			
			previewImg(){
			uni.previewImage({
				// 当前需要预览的图片
				current:"https://qpic.y.qq.com/music_cover/Kwg1Hs1pPD1YBDmLn9lwWcU93G4uWX0rKvHoGymiau22zalc3yu06pg/300?n=1",
				//所有图片
				urls:["https://qpic.y.qq.com/music_cover/Kwg1Hs1pPD1YBDmLn9lwWcU93G4uWX0rKvHoGymiau22zalc3yu06pg/300?n=1"]
			});			
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
