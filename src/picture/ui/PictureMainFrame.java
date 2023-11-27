package picture.ui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
/*
 * 主界面类
 */
public class PictureMainFrame extends JFrame {
	
	private String[] items = {"小女孩儿","女明星"};  //作为string数组到下拉框中
	private JRadioButton addNumInfo; // 数字提示
	private JRadioButton clearNumInfo; //清除提示
	private PictureCanvas canvas; // 拼图区
	private PicturePreview preview; //预览区
	private JComboBox<String> box; //下拉框
	private JTextField name; // 图片名称
	public static JTextField step; // 步数
	private JButton start;// 开始按钮
	
	//空参数构造方法
	public PictureMainFrame(){
		//super();
		init();//界面初始化操作
		
		addComponent();//添加组件
		
		addPreviewImage();//添加预览图片与拼图图片
		
		addActionListener();//为组件添加事件监听
	}

	//为组件添加事件监听
	private void addActionListener() {
		//数字提示
		addNumInfo.addActionListener(new ActionListener() {
			//点击时激活下面的方法
			@Override
			public void actionPerformed(ActionEvent e) {
				//完成数字提示的显示
				canvas.reLoadPictureAddNumber();
			}
		});
		
		//清除提示
		clearNumInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//完成数字提示的清除
				canvas.reLoadPictureClearNumber();
			}
		});
		
		//下拉框
		box.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				//获取到选择的图片的序号
				int num = box.getSelectedIndex();//默认从0开始
				//更新当前图片ID
				PictureCanvas.pictureID = num+1;
				//更新预览区
				preview.repaint();//重新绘制预览区界面*(can add new function)
				//更新拼图区
				canvas.reLoadPictureClearNumber();
				//更新游戏状态区
				name.setText("图片名称："+ box.getSelectedItem());//设置图片名称
				int stepNum = PictureCanvas.stepNum = 0;//游戏步数清零
				step.setText("步数：" + stepNum);//设置步数显示
				
				//按钮区
				//把按钮设置成 清除提示按钮选中状态
				clearNumInfo.setSelected(true);
			}
		});
		
		//开始按钮
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//已移动的步数清零
				PictureCanvas.stepNum = 0;
				//游戏状体区 进行步数显示更新
				step.setText("步数：" + PictureCanvas.stepNum);
				//对小方格进行重新位置排序，打乱顺序
				canvas.start();
			}
		});
	}

	//添加预览图片与拼图图片
	private void addPreviewImage() {
		//创建一个面板，包含拼图区与 预览区
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));//设置布局为 表格布局 设置两个区域
		//-------------
		//拼图区
		canvas = new PictureCanvas();
		canvas.setBorder(new TitledBorder("拼图区"));//添加边框
		
		//图片预览区
		preview = new PicturePreview();
		preview.setBorder(new TitledBorder("预览区"));//添加边框
		
		//把拼图区 与  图片预览区 添加到中间的面板中
		panel.add(canvas, BorderLayout.WEST);//左边
		panel.add(preview, BorderLayout.EAST);//右边
		
		//--------------
		//把面板显示在主界面中
		this.add(panel, BorderLayout.CENTER);//居中显示
	}

	//添加组件
	private void addComponent() {
		//创建用来在主界面上方显示的面板， 在面板中要包含按钮区 与 游戏状态区
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));  //设置表格布局(一行两个区域)（Could be modified）
		
		//创建左边按钮区面板
		JPanel leftPanel = new JPanel();
		leftPanel.setBorder(new TitledBorder("按钮区"));//添加边框
		leftPanel.setBackground(Color.PINK);//设置背景色（Could be modified）
		
		//添加单选按钮（添加我们新功能的按钮）
		addNumInfo = new JRadioButton("数字提示",false);
		clearNumInfo = new JRadioButton("清除提示",true);
		//添加按钮组
		ButtonGroup buttonGroup = new ButtonGroup();
		//添加下拉框
		box = new JComboBox<String>(items);
		//添加开始按钮
		start = new JButton("Start");
		
		//添加单选按钮到按钮组中，组中只有一个按钮能够被点击
		buttonGroup.add(addNumInfo);
		buttonGroup.add(clearNumInfo);
		//设置背景色
		addNumInfo.setBackground(Color.PINK);
		clearNumInfo.setBackground(Color.PINK);
		start.setBackground(Color.PINK);
		
		//添加组件到面板中
		leftPanel.add(addNumInfo);
		leftPanel.add(clearNumInfo);
		leftPanel.add(new JLabel("                选择图片："));
		leftPanel.add(box); //下拉框
		leftPanel.add(start);
		
		panel.add(leftPanel, BorderLayout.WEST);//添加到表格的左边
		//--------------------------------------
		//创建右边游戏状体区面板
		JPanel rightPanel = new JPanel();
		rightPanel.setBorder(new TitledBorder("游戏状态"));//添加带有标题的边框
		rightPanel.setBackground(Color.PINK);//设置背景色
		rightPanel.setLayout(new GridLayout(1, 2));//设置布局为表格布局！
		
		//添加组件 图片名称
		name = new JTextField("图片名称：小女孩儿");
		//添加组件 步数
		step = new JTextField("步数：0");
		
		//设置文本框不能编辑！
		name.setEditable(false);
		step.setEditable(false);
		
		//把组件添加到游戏状态面板中
		rightPanel.add(name, BorderLayout.WEST);//左边
		rightPanel.add(step, BorderLayout.EAST);//右边
		
		panel.add(rightPanel, BorderLayout.EAST);//添加到表格的右边
		//-------------------------------------
		//设置panel 在主界面的上方显示
		this.add(panel, BorderLayout.NORTH);//上面
	}

	/*
	 * 界面初始化方法
	 */
	private void init() {
		//设置窗口的标题
		this.setTitle("拼图游戏");
		//设置窗口的大小
		this.setSize(1000, 720);
		//设置窗口的显示位置
		this.setLocation(150, 10);
		//设置窗口的大小为固定大小
		this.setResizable(false);
		//设置窗口的默认关闭操作
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
