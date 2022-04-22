<template>
	<view>
		<!-- header -->
		<view class="header_img">
			<image v-if="category" src="../../static/category_header_cat.png" mode="aspectFit"></image>
			<image v-else src="../../static/category_header_flower.png" mode="aspectFit"></image>
		</view>
		
		<!-- 左侧选择框 -->
		<view class="sideBar" style="z-index: 999;">
			<view class="sideBar_trans">
				<button class="" type="default" @click="changeCategory()">切 换</button>
			</view>
			
			<view class="buttons" v-for="(item, index) in selectContentList" :key="index">
				<button type="default" :buttonName='index' @click="throttleSelect(index)">{{ item }}</button>
			</view>
			
		</view>
		
		<view class="content">
			<!-- 分类标题 -->
			<view class="content_title">
				{{ contentTitle }}
				<view class="content_title_line">
				</view>
			</view>
            <!-- 加载动画 -->
			<view v-show="loading" class="loading">
			<image src="../../static/loading.gif" class="loading-img"></image>	
			</view>
			<!-- 分类内容 -->
			<view v-show="!loading" class="content_main">
				<view class="content_main_item" v-for="item in contentList" :key="item" @click="toList(item.florescence || item.department || item.color)">

					<view class="content_main_item_text" v-show="category==0 && index == 1 && item.florescence">
						{{ item.florescence }}
					</view>
					<view class="content_main_item_text" v-show="category==0 && index == 2 && item.department">
						{{ item.department }}
					</view>
					<view class="content_main_item_text" v-show="index == 0 && item.color">
						{{ item.color }}
					</view>
					<u--image class="content_main_item_image" :src="item.picture.store" lazyload="true" mode="aspectFill" width="230rpx" height="348rpx">
					  <template v-slot:loading>
					    <u-loading-icon mode="circle" color="red"></u-loading-icon>
					  </template>
					</u--image>	
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import { throttle } from'../../util/throttle.js'
	import api from '@/api/api.js'
	export default {
		name: 'throttle',
		data() {
			return {
				category:'',
				index: '111',
				contentTitle: '花花分类',
				selectContentList:[],
				loading:false,
				selectFlowerList: [
					'花色',
					'花期',
					'品种',
					'生长位置'
				],
				selectCatList: [
					'毛色',
					'出没位置'
				],
				contentList: [              //对象数组，包含类别名称和具有该类别的典型特征的猫/花的图片
					{
						florescence: '',
						department: '',
						color: '',
						picture: {
							store: ''
						}
					}
				]
			}
		},
		methods: {
			changeCategory() {
				this.loading=true;
				this.contentList="";
				if(this.category == 0) {
					this.category = 1;
					this.contentTitle = '猫猫分类';
					this.selectContentList = this.selectCatList;
					this.selectCategory(0);
				}
				else {
					this.category = 0;
					this.contentTitle = '花花分类';
					this.selectContentList = this.selectFlowerList;
					this.selectCategory(0);
				}
				// 获取两个大类中选中的一个的默认第一个小类中具体内容，即猫的毛列表和花的花色列表
			},
			selectCategory(index) {
				// 获取用户选中的按钮的信息，并作为传给后端的参数
				// 获取详细分类方式list，即左侧选择分类按钮
				this.loading=true;
				this.contentList="";
				if(this.category) {
					if(index == 0) {
						this.index = index
						uni.setStorageSync('categoryitem', this.selectCatList[index])
						api.catColorCategory().then(res => {
							this.contentList = res;
						    this.loading=false;
						})
					}
				}
				else {
					if(index == 0) {
						this.index = index
						uni.setStorageSync('index',this.index)
						uni.setStorageSync('categoryitem', this.selectFlowerList[index])
						api.flowerColorCategory().then(res => {
							this.contentList = res;
						    this.loading=false;
						})
					}
					else if(index == 1) {
						this.index = index
						uni.setStorageSync('categoryitem', this.selectFlowerList[index])
						api.flowerStateCategory().then(res => {
							this.contentList = res;
							this.loading=false;
						})
					}
					else if(index == 2) {
						this.index = index
						uni.setStorageSync('categoryitem', this.selectFlowerList[index])
						api.flowerVarietyCategory().then(res => {
							this.contentList = res;
							this.loading=false;
						})
					}
				}
				
			},
			toList(data) {
				uni.setStorageSync('categoryindex', this.category)
				uni.navigateTo({
					url:'../../packageA/pages/list/list?data='+data
				})
			},
			throttleClick: throttle(function(...args) {
				console.log(...args)
				this.selectCategory(...args)

			}),
			throttleSelect(index) {
				if(index == this.index) {
					this.throttleClick(index)
				}
				else {
					this.selectCategory(index)
				}
			}
		},
		mounted() {
			this.selectContentList = this.selectFlowerList
			this.selectCategory(0)
		}
	}
</script>

<style scoped>
.header_img {
	overflow: hidden;
	position: absolute;
	left: 0rpx;
	top: 0rpx;
	width: 750rpx;
	height: 438rpx;
	/* transform: rotate(180deg); 翻转*/
	border-radius: 0rpx 0rpx 58rpx 58rpx;
	background: #FAACA8;
	filter: blur(10rpx);
}

.header_img_flower {
	position: absolute;
	left: 73rpx;
}

.sideBar {
	position: fixed;
	left: 15rpx;
	top: 362rpx;
	width: 200rpx;
	height: 562rpx;
	border-radius: 10px;
	background: #FFFFFF;
	
}

.sideBar_trans button:first-child {
	background-color: #FAACA8;
	width: 152rpx;
	height: 62rpx;
	margin-top: 19rpx;
	border-radius: 3px;
	font-size: 26rpx;
	font-weight: 900;
	color: #ffffff;
}

.buttons button {
	width: 175rpx;
	line-height: 70rpx;
	margin-top: 25rpx;
	border-radius: 3px;
	background: #F0ECEC;
	font-size: 28rpx;
	color: #9E9E9E;
}

.buttons button:active{
	background-color: #FAACA8;
	color: #ffffff;
}

.content {
	position: absolute;
	top: 363rpx;
	width: 750rpx;
}

.content_title {
	margin-left: 233rpx;
	width: 487rpx;
	line-height: 121rpx;
	border-radius: 9px;
	background: #FFFFFF;
	text-align: center;
	font-size: 46rpx;
	color: #9E9E9E;
}

.content_title_line {
	position: absolute;
	margin-left: 140rpx;
	top: 98rpx;
	width: 212rpx;
	height: 0rpx;
	border: 2rpx solid #FAACA8;
}

.content_main {
	display: flex;
	flex-wrap: wrap;
	/* width: 775rpx; */
	margin-bottom: 31rpx;
	margin-left: 205rpx;
}

.content_main_item {
	margin-top: 31rpx;
	margin-left: 31rpx;
	width: 230rpx;
	height:333rpx;
	border-radius: 10px;
	background: #FFFFFF;
	overflow: hidden;
	position: relative;
}

.content_main_item_image {
	width: 100%;
	height: 100%;
	z-index: 1;
}

.content_main_item_text {
    width: inherit;
    line-height: 64rpx;
    position: absolute;
    bottom: 0rpx;
    text-align: center;
    font-size: 31rpx;
    font-weight: 300;
    color: #ffffff;
    z-index: 3;
    backdrop-filter: blur(2px);
}
.loading
{
	width: 478rpx;
	height: 300rpx;
	border-radius: 20rpx;
	background-color: #FFFFFF;
	margin-top: 31rpx;
	margin-left: 239rpx;
}
.loading-img
{
	width: 150rpx;
	height: 150rpx;
	margin-left: 164rpx;
	margin-top: 80rpx;
}
</style>
