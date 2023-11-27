# Introduction of Functions
> Reference to [Link](https://pan.baidu.com/wap/init?surl=ocwOl1vwq9TffLXGO9VXyQ)，password:**_n7zp_**

## [MainApp](./src/picture/app/MainApp.java)
* main():
  * 创建主界面类对象[PictureMainFrame](./src/picture/ui/PictureMainFrame.java)
    1. **init()** 界面初始化操作： 
       * 窗体大小（且固定大小）
       * 游戏标题
       * 游戏窗口的显示位置
    2. **addComponent()** 添加组件方法
       * 按钮区的组件设置
       * 完成游戏状态的组件设置
    3. **addPicturePreview()** 添加拼图游戏预览与拼图界面
       * 完成右侧的 预览图片的设置
       * 完成坐标的 拼图图片的设置
  * 显示界面
---
    其中随机打乱算法是当第一个小方格（左上角）距离左上角较近的时候进行方格与空方格的互换
# TODO:
1. 更改可视化界面 
    **重新设置控件布局**
2. 更改图片 **程序中作者的处理是手动用PS software 分割图片成4x3块.gif的，后进行读取**
3. 增加功能：设置难度，将图片分成更多份 **需要改变code很多逻辑部分，因为code不具有robust，因此可以尝试优化随机算法增加难度**
4. 增加功能：自动呈现答案逻辑顺序 **需要先查找对应算法**
