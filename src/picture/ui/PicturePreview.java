package picture.ui;

import javax.swing.*;
import java.awt.*;
/*
 * ͼƬԤ����
 */
public class PicturePreview extends JPanel {
	//��д���������������ʾͼƬ����ʾ
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//ָ��ͼƬ��·��
		//String filename = "picture\\1.jpg";
		String filename = "picture/"+PictureCanvas.pictureID+".jpg";
		//ͨ��ͼƬ��·������ȡ����Ӧ��ͼƬ�е�ͼ��
		ImageIcon icon = new ImageIcon(filename);
		Image image = icon.getImage();
		//��ͼ�� ������ Ԥ�����������
		g.drawImage(image, 20, 20, 450, 600, this);
	}
}
