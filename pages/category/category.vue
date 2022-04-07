<template>
	<view>
		<!-- header -->
		<view class="header_img">
			<image v-if="category" src="../../static/category_header_flower.png" mode="aspectFit"></image>
			<image v-else src="../../static/category_header_cat.png" mode="aspectFit"></image>
		</view>
		
		<!-- 左侧选择框 -->
		<view class="sideBar" style="z-index: 999;">
			<view class="sideBar_trans">
				<button class="" type="default" @click="changeCategory">切 换</button>
			</view>
			
			<view class="buttons" v-for="(item, index) in selectContentList" :key="index">
				<button type="default" :buttonName='index' @click="selectCategory">{{ item }}</button>
			</view>
			
			<!-- <view class="buttons" v-else >
				<button type="default" buttonName='catColor'>毛色</button>
				<button type="default" buttonName='catPosition'>出没位置</button>
			</view> -->
		</view>
		
		<view class="content">
			<!-- 分类标题 -->
			<view class="content_title">
				{{ contentTitle }}
				<view class="content_title_line">
				</view>
			</view>
			
			<!-- 分类内容 -->
			<view class="content_main">
				<view class="content_main_item" v-for="item in contentList" :key="item" @click="toList">
					<image class="content_main_item_image" src="../../static/cat_img/3.jpg" mode="aspectFill"></image>
					<view class="content_main_item_text">
						{{ item }}
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				category: 1,
				contentTitle: '花花分类',
				// selectButton: buttonName,
				selectContentList: [
					'花色',
					'花期',
					'品种',
					'生长位置'
				],
				contentList: [              //对象数组，包含类别名称和具有该类别的典型特征的猫/花的图片
					'红',
					'黄',
					'蓝',
					'绿',
					'白'
				]
			}
		},
		created() {
			// 获取默认大类的小类列表
			// 获取默认小类中的具体内容
		},
		methods: {
			changeCategory() {
				if(this.category == 1) {
					this.category = 0;
					this.contentTitle = '猫猫分类';
				}
				else {
					this.category = 1;
					this.contentTitle = '花花分类';
				}
				
				// 获取详细分类方式list，即左侧选择分类按钮
				// 获取两个大类中选中的一个的默认第一个小类中具体内容，即猫的毛列表和花的花色列表
			},
			selectCategory() {
				console.log('select')
				// 获取用户选中的按钮的信息，并作为传给后端的参数
				// 获取相应数据
			},
			toList() {
				uni.navigateTo({
					url:'../../packageA/pages/list/list'
				})
			}
		}
	}
</script>

<style>
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
	/* top: 500rpx; */
	/* width: 20rpx;
	height: 20rpx; */
	/* opacity: 50%; */
	/* filter: blur(10rpx); */
}

.sideBar {
	position: fixed;
	left: 15rpx;
	top: 400rpx;
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
	/* position: absolute; */
	/* left: 23px;
	top: 23px; */
	width: 175rpx;
	line-height: 70rpx;
	margin-top: 25rpx;
	border-radius: 3px;
	background: #F0ECEC;
	font-size: 28rpx;
	color: #9E9E9E;
}

.content {
	/* display: flex;*/
	position: absolute;
	top: 363rpx;
	width: 750rpx;
}

.content_title {
	/* position: absolute; */
	margin-left: 233rpx;
	/* top: 0rpx; */
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
	height: 348rpx;
	border-radius: 10px;
	background: #FFFFFF;
	overflow: hidden;
	position: relative;
}

.content_main_item_image {
	width: 100%;
	height: 100%;
	/* object-fit: inherit; */
}

.content_main_item_text {
	width: inherit;
	line-height: 60rpx;
	/* background-color: #C8C7CC; */
	/* opacity: 80%; */
	background:rgba(200, 199, 204, 0.6);
	position: absolute;
	bottom: 0rpx;
	text-align: center;
	font-size: 36rpx;
	color: #FFFFFF;
	/* back-fliter: blur(10rpx); */	
}
</style>
