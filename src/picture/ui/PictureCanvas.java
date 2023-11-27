package picture.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/*
 * ƴͼ��
 */
public class PictureCanvas extends JPanel implements MouseListener{
	//��̬����
	public static int pictureID = 1; //ͼƬID
	public static int stepNum = 0; //����
	
	//��Ա����
	private Cell[] cell;//С����
	private boolean hasAddActionListener =  false; //������ʾ�Ƿ�Ϊ��������˵�������������ֵΪtrue,δ���ֵΪfalse
	private Rectangle nullCell;//ָ��һ���յ�С����
	
	//���췽��
	public PictureCanvas(){
		//����ƴͼ���Ĳ���
		this.setLayout(null);//֡����
		//����12��ͼƬС������ӵ�ƴͼ��
		cell = new Cell[12];
		for (int i = 0; i < 4; i++) {//�����������
			for (int j = 0; j < 3; j++) {//�����������
				//����ͼƬ
				//ImageIcon icon = new ImageIcon("picture\\1_1.gif");
				//i=0,1
				//j=0,1,2
				//0*3+0+1 = 1
				//0*3+1+1 = 2
				//0*3+2+1 = 3
				//1*3+0+1 = 4
				//1*3+1+1 = 5
				ImageIcon icon = new ImageIcon("picture/1_"+(i*3+j+1)+".gif");
				//����ͼƬС����
				cell[i*3+j] = new Cell(icon);
				//ָ��ͼƬС������ʾ��λ��
				cell[i*3+j].setLocation(j*150+20, i*150+20);
				//��ͼƬС������ӵ�ƴͼ����ʾ
				this.add(cell[i*3+j]);
			}
		}
		
		//ɾ����12��ͼƬС����
		this.remove(cell[11]);
		
		//ָ��һ���յ�С����
		nullCell = new Rectangle(300+20, 450+20, 150, 150);
	}
	
	//���¼���ͼƬ�������������ʾ
	public void reLoadPictureAddNumber(){
		//��ȡ��ÿһ��ͼƬС����
		for (int i = 0; i < 4; i++) {//����
			for (int j = 0; j < 3; j++) {//����
				//��ȡС����  cell[i*3+j]
				//����С������ʾ��ͼƬ
				ImageIcon icon = new ImageIcon("picture/"+pictureID+"_"+(i*3+j+1)+".gif");
				cell[i*3+j].setIcon(icon);
				//����С������ʾ��������ʾ
				cell[i*3+j].setText("" + (i*3+j+1));
				//����С����������ʾ��λ��
				cell[i*3+j].setVerticalTextPosition(this.getY()/2);//�������ִ�ֱ��ʾλ��[������ʾ]
				cell[i*3+j].setHorizontalTextPosition(this.getX()/2);//��������ˮƽ��ʾλ��[������ʾ]
			}
		}
		
	}
	
	//���¼���ͼƬ����ȥ��������ʾ
	public void reLoadPictureClearNumber(){
		//��ȡ��ÿһ��ͼƬС����
		for (int i = 0; i < 4; i++) {//����
			for (int j = 0; j < 3; j++) {//����
				//��ȡС����  cell[i*3+j]
				//����С������ʾ��ͼƬ
				ImageIcon icon = new ImageIcon("picture/"+pictureID+"_"+(i*3+j+1)+".gif");
				cell[i*3+j].setIcon(icon);
				//����С������ʾ��������ʾ
				cell[i*3+j].setText("");
			}
		}
	}
	
	//��С�����������λ�����򣬴���˳��
	public void start(){
		//���û�и�С�����������������Ļ�������Ӽ���
		if ( !hasAddActionListener) {
			//��Ӽ���
			for (int i = 0; i < 11; i++) {
				cell[i].addMouseListener(this);
			}
			//���������������״̬
			hasAddActionListener = true;
		}
		
		//�жϵ�ǰ��һ��С����������Ͻ��ĽϽ�ʱ�����з�����շ��񻥻�
		//�����һ��С���������Ͻǵ��ĸ�����λ���ڣ��Ͳ��ϵ�ѭ�������з�����շ���λ�õĻ���
		while(cell[0].getBounds().x <=170 && cell[0].getBounds().y <=170 ){
			//��ȡ���շ����λ��
			int nullX = nullCell.getBounds().x;
			int nullY = nullCell.getBounds().y;
			
			//�������һ�����򣬽��пշ�����С�����Ļ���
			//����0-3֮������������Ӧ�շ�������������ƶ�
			int direction = (int)(Math.random()*4);//0,1,2,3
			switch (direction) {
				case 0://�շ��������ƶ�������ߵķ�����л���λ�ã���෽��Ҫ�����ƶ�
					//nullX = nullX - 150;
					nullX -= 150;
					cellMove(nullX, nullY, "RIGHT");
					break;
				case 1://�շ��������ƶ������ұߵķ�����л���λ�ã��Ҳ෽��Ҫ�����ƶ�
					nullX += 150;
					cellMove(nullX, nullY, "LEFT");
					break;
				case 2://�շ��������ƶ������ϱߵķ�����л���λ�ã��ϲ෽��Ҫ�����ƶ�
					nullY -= 150;
					cellMove(nullX, nullY, "DOWN");
					break;
				case 3://�շ��������ƶ������±ߵķ�����л���λ�ã��²෽��Ҫ�����ƶ�
					nullY += 150;
					cellMove(nullX, nullY, "UP");
					break;
			}
		}
		
	}

	/**
	 * ������շ���λ�û���
	 * @param nullX �շ����x������
	 * @param nullY �շ����y������
	 * @param direction ����Ҫ�ƶ��ķ���
	 */
	private void cellMove(int nullX, int nullY, String direction) {
		for (int i = 0; i < 11; i++) {
			//��ȡ����շ���λ����ͬ��С����
			if (cell[i].getBounds().x == nullX && cell[i].getBounds().y == nullY) {
				//��ǰ������ƶ�
				cell[i].move(direction);
				//�շ�����ƶ�
				nullCell.setLocation(nullX, nullY);
				//����λ�ú󣬽���ѭ��
				break;
			}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {//��갴��ȥ
		//��ȡ����ǰ�������С����
		Cell button = (Cell)e.getSource();
		//��ȡ����������x��y����
		int clickX = button.getBounds().x;
		int clickY = button.getBounds().y;
				
		//��ȡ��ǰ�շ����x��y����
		int nullX = nullCell.getBounds().x;
		int nullY = nullCell.getBounds().y;
		
		//���бȽϣ����������������λ�õĽ���
		if (clickX == nullX && clickY - nullY == 150) {//�����Ϊ�շ�������ķ���
			button.move("UP");//������ķ��� �����ƶ�
		} else if (clickX == nullX && clickY - nullY == -150) {//�����Ϊ�շ�������ķ���
			button.move("DOWN");//������ķ��� �����ƶ�
		} else if(clickX - nullX == 150 && clickY == nullY){//�����Ϊ�շ�������ķ���
			button.move("LEFT");//������ķ��� �����ƶ�
		} else if(clickX - nullX == -150 && clickY == nullY){//�����Ϊ�շ�������ķ���
			button.move("RIGHT");//������ķ��� �����ƶ�
		} else {
			return ;// �������ƶ��������Ͳ������κεĴ���
		}
		//���¿շ����λ��
		nullCell.setLocation(clickX, clickY);
		//ƴͼ���������»���
		this.repaint();
		//���²���������Ϸ״̬���Ĳ�������
		stepNum++;
		PictureMainFrame.step.setText("������"+stepNum);
		
		//�жϵ�ǰ��Ϸ�Ƿ���ɣ�����ɣ������һ���Ѻ���ʾ
		if (this.isFinish()) {
			//����������ʾ
			JOptionPane.showMessageDialog(this, "��ϲ�����ƴͼ�����ͣ� \n ���ò�:"+stepNum);
			//����ÿһ��С�����ϵ����������, �������С��������������
			for (int i = 0; i < 11; i++) {
				cell[i].removeMouseListener(this);
			}
			//���·���Ķ�����������״̬
			hasAddActionListener = false;
		}
		
	}
	
	//�жϵ�ǰ��Ϸ�Ƿ����, ���������ж��Ƿ�ƴͼ�ɹ�
	private boolean isFinish(){
		for (int i = 0; i < 11; i++) {
			//��ȡÿһ�������λ��
			int x = cell[i].getBounds().x;
			int y = cell[i].getBounds().y;
			if ( ((y-20)/150*3 + (x-20)/150) != i ) {
				//i=0,  (20-20)/150*3 + (20-20)/150) = 0
				//i=1,  (20-20)/150*3 + (170-20)/150) = 1
				//....
				//i=5,  (170-20)/150*3 + (320-20)/150)= 5 
				return false;
			}
		}
		
		return true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	
	
	
	
	
	
	
	
	
}
