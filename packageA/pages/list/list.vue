<template>
	<view>
		<view class="header">
			<image class="header_img" :src="categoryList[0].picture.store" mode="aspectFill"></image>
		</view>
		<image @click="backToLast" src="../../../static/back-icon.png" class="back"></image>
		<view class="content">
			<view class="content_title">
				{{ categoryTitle }}
				<view class="content_title_line">
				</view>
			</view>
			<scroll-view class="content_main" scroll-x="true">
				<view class="content_main_item" v-for="item in categoryList" :key="item">
					
					<view class="content_item_imgbox">
					    <u--image class="content_item_img" :src="item.picture.store" mode="aspectFill" @click="previewImg(item.picture.store)" width="471rpx" height="577rpx">
					      <template v-slot:loading>
					        <u-loading-icon color="red"></u-loading-icon>
					      </template>
					    </u--image>
					</view>
					<view class="content_sub" @click="toDetail(item)">
						<view class="content_item_line">
							<view class="content_item_name">
								{{ item.name }}
							</view>
						</view>
						
						<image class="content_item_more"src="../../../static/more.png" mode="aspectFit"></image>
					</view>
				</view>
				
			</scroll-view>
		</view> 
	</view>
</template>

<script>
	import api from '@/api/api.js'
	export default {
		data() {
			return {
				scrollTop: 0,
				old: {
				    scrollTop: 0
				},
				categoryTitle: 'loading...',
				categoryList: [
					{
						cid: '1',
						name: 'Loading...',
						picture: {
							pid: '1',
							store: 'https://somecute.cn/img/static/flower_list.jpg'
						}
					}
				]
			}
		},
		onLoad(option) {
			if(uni.getStorageSync('categoryindex') == 1) {
				if(uni.getStorageSync('categoryitem') == '毛色') {
					api.catSort({color: option.data}).then(res => {
						this.categoryTitle = option.data
						this.categoryList = res
					})
				}
			}
			else {
				uni.setStorageSync('categoryindex', 2) 
				
				if(uni.getStorageSync('categoryitem') == '花色') {
					api.flowerSortColor({color: option.data}).then(res => {
						this.categoryTitle = option.data
						this.categoryList = res
					})
				}
				else if(uni.getStorageSync('categoryitem') == '花期') {
					api.flowerSortState({florescence: option.data}).then(res => {
						this.categoryTitle = option.data
						this.categoryList = res
					})
				}
				else if(uni.getStorageSync('categoryitem') == '品种') {
					api.flowerSortVariety({department: option.data}).then(res => {
						this.categoryTitle = option.data
						this.categoryList = res
					})
				}
			}
		},
		methods: {
			backToLast(){
				let pages = getCurrentPages()
				uni.navigateBack({
					url:pages[pages.length-2].route
				})
			},
			toDetail(data) {
				let id = ''
				if(uni.getStorageSync('categoryindex') == 1) {
						id = data.picture.pid
						// id = data.cid
					}
					else {
						id = data.picture.pid
					}
				uni.navigateTo({
					url:'../detail/detail?data='+id
				})
			},
			previewImg(data){
				uni.previewImage({
					// 当前需要预览的图片
					current:data,
					// //所有图片
					urls:[data]
				});			
			}
		}
	}
</script>

<style>
.header {
	position: relative;
	left: 0rpx;
	top: 0rpx;
	width: 750rpx;
	height: 500rpx;
	filter: blur(10rpx);
}

.back {
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

.header_img {
	width: 100%;
	height: 100%;
}

.content {
	/* display: block; */
	position: absolute;
	/* top: 272px; */
	width: 750rpx;
	height:1200rpx;
	/* border-radius: 19rpx; */
	background: #F1FCFD;
	box-shadow: 0px 4px 10px 0px rgba(0, 0, 0, 0.3);
}

.content_title {
	position: absolute;
	left: 175rpx;
	top: -57rpx;
	width: 400rpx;
	line-height: 142rpx;
	border-radius: 19rpx;
	background: #FFFFFF;
	box-shadow: 0px 4px 10px 0px rgba(0, 0, 0, 0.3);
	text-align: center;
	font-size: 46rpx;
	color: #9E9E9E;
}

.content_title_line {
	position: absolute;
	margin-left: 140rpx;
	top: 105rpx;
	width: 120rpx;
	height: 0rpx;
	border: 2rpx solid #FAACA8;
}

.content_main {
	margin-top: 202rpx;
	height: 865rpx;
	/* background-color: #F0AD4E; */
	/* !!! */
	white-space: nowrap;
}

.content_main_item {
	position: relative;
	/* !!! */	
	display: inline-block; 
	/* margin-left: 15px; */
	margin-right: 58rpx;
	width: 538rpx;
	height: 788rpx;
	background: #FFFFFF;
	box-shadow: 0px 4px 10px 0px rgba(0, 0, 0, 0.3);
}

.content_main_item:first-child {
	margin-left: 58rpx;
}

.content_main_item:last-child {
	margin-right: 58rpx;
}

.content_item_imgbox {
	position: absolute;
	left: 33rpx;
	top: 38rpx;
	width: 471rpx;
	height: 577rpx;
	/* overflow: hidden; */
}

.content_item_img {
	width: 100%;
	height: 100%;
}

.content_sub {
	width: inherit;
	height: 160rpx;
	margin-top: 620rpx;
}

.content_item_name {
	position: absolute;
	left: 33rpx;
	top: 654rpx;
	font-size: 46rpx;
}

.content_item_like {
	position: absolute;
	left: 452rpx;
	top: 654rpx;
	width: 46rpx;
	height: 46rpx;
}

.content_item_more {
	position: absolute;
	left: 438rpx;
	top: 712rpx;
	width: 73rpx;
	height: 73rpx;
}
</style>
