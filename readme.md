# 接口文档

### 接口一：猫猫详情

接口相对路径：/cat/catdetail

传入参数：Picture对象，属性包括

- uid
- pid

返回值类型：Cat

![image-20220407132704752](C:\Users\olig1\AppData\Roaming\Typora\typora-user-images\image-20220407132704752.png)

![image-20220407132735414](C:\Users\olig1\AppData\Roaming\Typora\typora-user-images\image-20220407132735414.png)

![image-20220407132802157](C:\Users\olig1\AppData\Roaming\Typora\typora-user-images\image-20220407132802157.png)

![image-20220407132828727](C:\Users\olig1\AppData\Roaming\Typora\typora-user-images\image-20220407132828727.png)

### 接口二：猫猫搜索

相对路径：/cat/search

传入参数：Picture对象，属性包括

- uid
- information

返回值类型：List< Picture>

![image-20220407133145164](C:\Users\olig1\AppData\Roaming\Typora\typora-user-images\image-20220407133145164.png)

### 接口三：猫猫分类

相对路径：/cat/classifycolor

传入参数：无

返回值类型：List< Cat>

![image-20220407133345501](C:\Users\olig1\AppData\Roaming\Typora\typora-user-images\image-20220407133345501.png)

![image-20220407133421032](C:\Users\olig1\AppData\Roaming\Typora\typora-user-images\image-20220407133421032.png)

### 接口四：花花照片详情

相对路径：/flower/flowerdetail

传入参数：Picture对象，属性包括

- uid
- pid

返回值类型：Flower对象

![image-20220407133639680](C:\Users\olig1\AppData\Roaming\Typora\typora-user-images\image-20220407133639680.png)

![image-20220407133657700](C:\Users\olig1\AppData\Roaming\Typora\typora-user-images\image-20220407133657700.png)

### 接口五：花花搜索

相对路径：/flower/search

传入参数：Picture对象，属性包括

- uid
- information

返回值类型：List< Picture>

![image-20220407133853889](C:\Users\olig1\AppData\Roaming\Typora\typora-user-images\image-20220407133853889.png)

### 接口六：花花分类按照花期

相对路径：/flower/classifyflorescence

传入参数:无

返回值类型：List< Flower>

![image-20220407134037533](C:\Users\olig1\AppData\Roaming\Typora\typora-user-images\image-20220407134037533.png)

![image-20220407134119476](C:\Users\olig1\AppData\Roaming\Typora\typora-user-images\image-20220407134119476.png)

![image-20220407134149063](C:\Users\olig1\AppData\Roaming\Typora\typora-user-images\image-20220407134149063.png)

### 接口七：花花分类按照品种

相对路径：/flower/classifyvariety

传入参数:无

返回值类型：List< Flower>

![image-20220407134252252](C:\Users\olig1\AppData\Roaming\Typora\typora-user-images\image-20220407134252252.png)

### 接口八：为照片点赞

相对路径：/picture/like

传入参数：Picture对象，属性包括

- uid
- pid

返回类型：String的提示信息（不过不成功的情况我好像还没写）

![image-20220407134452282](C:\Users\olig1\AppData\Roaming\Typora\typora-user-images\image-20220407134452282.png)

### 接口九：主页推荐

相对路径：/picture/recommend

传入参数：Picture对象，属性包括

- uid
- type(判断是猫还是花)

返回值类型：List< Picture>

![image-20220407134729804](C:\Users\olig1\AppData\Roaming\Typora\typora-user-images\image-20220407134729804.png)

![image-20220407134752875](C:\Users\olig1\AppData\Roaming\Typora\typora-user-images\image-20220407134752875.png)

![image-20220407134916847](C:\Users\olig1\AppData\Roaming\Typora\typora-user-images\image-20220407134916847.png)

### 接口十：用户喜欢

相对路径：/picture/user

传入参数：Picture对象，属性包括

- uid
- type

返回值类型：List< Picture>

![image-20220407135301686](C:\Users\olig1\AppData\Roaming\Typora\typora-user-images\image-20220407135301686.png)

![image-20220407135438480](C:\Users\olig1\AppData\Roaming\Typora\typora-user-images\image-20220407135438480.png)