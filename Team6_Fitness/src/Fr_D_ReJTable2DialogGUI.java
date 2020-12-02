import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Fr_D_ReJTable2DialogGUI extends JDialog implements ActionListener {
	private static final long serialVersionUID = -8926950592764879152L;
	
	JPanel pw;
	JPanel pc;
	JPanel ps;

	JLabel lable_R_id;
	JLabel lable_Rm_id;
	JLabel lable_Re_id;

	JTextField r_id;
	JTextField rm_id;
	JTextField re_id;
	
	JComboBox<String> cbE_id;
	JComboBox<String> cbM_id;

	JButton confirm;
	JButton reset;

	Fr_M_MemberMain rejt2ob;
	Fr_M_AdminMain rejt2ob2;

	JPanel ridCkP;
	JButton ridCkBtn;

	DB_ReJTable2DAO redao2;

	public Fr_D_ReJTable2DialogGUI(Fr_M_MemberMain rejt2ob, String index) {
		super(rejt2ob, "�����߰�/����");
		
		pw = new JPanel(new GridLayout(4, 1));
		pc = new JPanel(new GridLayout(4, 1));
		ps = new JPanel();

		lable_R_id = new JLabel("���� ��ȣ");
		lable_Rm_id = new JLabel("ȸ�� ID");
		lable_Re_id = new JLabel("� ID");

		r_id = new JTextField();
		rm_id = new JTextField();
		re_id = new JTextField();
		
		cbE_id = new JComboBox<String>();
		cbM_id = new JComboBox<String>();

		reset = new JButton("���");

		ridCkP = new JPanel(new BorderLayout());
		ridCkBtn = new JButton("R_IDCheck");

		redao2 = new DB_ReJTable2DAO();
		
		this.rejt2ob = rejt2ob;
		if (index.equals("�߰�")) {
			confirm = new JButton(index);
			r_id.setEnabled(false);
		} else {
			confirm = new JButton("����");

			// text�ڽ��� ���õ� ���ڵ��� ���� �ֱ�
			int row = rejt2ob.rejt2.getSelectedRow();// ���õ� ��
			r_id.setText(rejt2ob.rejt2.getValueAt(row, 0).toString());
			rm_id.setText(rejt2ob.rejt2.getValueAt(row, 1).toString());
			re_id.setText(rejt2ob.rejt2.getValueAt(row, 2).toString());

			// r_id text�ڽ� ��Ȱ��
			r_id.setEditable(false);

			// R_IDCheck��ư ��Ȱ��ȭ
			ridCkBtn.setEnabled(false);
		}

		pw.add(lable_R_id);
		pw.add(lable_Rm_id);
		pw.add(lable_Re_id);

		ridCkP.add(r_id, "Center");
		ridCkP.add(ridCkBtn, "East");

		pc.add(ridCkP);
		pc.add(rm_id);
		pc.add(re_id);

		ps.add(confirm);
		ps.add(reset);

		add(pw, "West");
		add(pc, "Center");
		add(ps, "South");

		setSize(300, 250);
		setVisible(true);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		// �̺�Ʈ���
		confirm.addActionListener(this); // �߰�,������ư ���̾�α� �̺�Ʈ���
		reset.addActionListener(this); // ��� �̺�Ʈ���
		ridCkBtn.addActionListener(this);// r_id�ߺ�üũ �̺�Ʈ ���

	}
	
	public Fr_D_ReJTable2DialogGUI(Fr_M_AdminMain rejt2ob, String index) {
		super(rejt2ob, "�����߰�/����");
		
		pw = new JPanel(new GridLayout(4, 1));
		pc = new JPanel(new GridLayout(4, 1));
		ps = new JPanel();

		lable_R_id = new JLabel("���� ��ȣ");
		lable_Rm_id = new JLabel("ȸ�� ID");
		lable_Re_id = new JLabel("� ID");

		r_id = new JTextField();
		rm_id = new JTextField();
		re_id = new JTextField();
		
		cbE_id = new JComboBox<String>();
		cbM_id = new JComboBox<String>();

		reset = new JButton("���");

		ridCkP = new JPanel(new BorderLayout());
		ridCkBtn = new JButton("R_IDCheck");

		redao2 = new DB_ReJTable2DAO();
		
		this.rejt2ob2 = rejt2ob;
		
		DB_MemberDAO dao = new DB_MemberDAO();
		ArrayList<DB_MemberDTO> Dto = dao.memberSelectM_id();
		for(int i = 0; i < Dto.size(); i ++) {
			if(!Dto.get(i).getM_id().equals("admin")) {
				cbM_id.addItem(Dto.get(i).getM_id());
			}			
		}
		DB_PtDAO ptdao = new DB_PtDAO();
		ArrayList<DB_PtDTO> ptDto = ptdao.ptSelectE_id();
		for(int i = 0; i < ptDto.size(); i ++) {
			cbE_id.addItem(ptDto.get(i).getE_teacher());
		}
		
		if (index.equals("�߰�")) {
			confirm = new JButton(index);
			r_id.setEditable(false);
		} else {
			confirm = new JButton("����");

			// text�ڽ��� ���õ� ���ڵ��� ���� �ֱ�
			int row = rejt2ob.rejt2.getSelectedRow();// ���õ� ��
			r_id.setText(rejt2ob.rejt2.getValueAt(row, 0).toString());
//			rm_id.setText(rejt2ob.rejt2.getValueAt(row, 1).toString());
//			re_id.setText(rejt2ob.rejt2.getValueAt(row, 2).toString());

			// r_id text�ڽ� ��Ȱ��
			r_id.setEditable(false);
//			rm_id.setEditable(false);
			
			cbM_id.setSelectedItem(rejt2ob.rejt2.getValueAt(row, 1).toString());
			cbE_id.setSelectedItem(rejt2ob.rejt2.getValueAt(row, 2).toString());

		}

		pw.add(lable_R_id);
		pw.add(lable_Rm_id);
		pw.add(lable_Re_id);

		pc.add(r_id);
		pc.add(cbM_id);
//		pc.add(rm_id);
		pc.add(cbE_id);
//		pc.add(re_id);

		ps.add(confirm);
		ps.add(reset);

		add(pw, "West");
		add(pc, "Center");
		add(ps, "South");

		setSize(300, 250);
		setVisible(true);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		// �̺�Ʈ���
		confirm.addActionListener(this); // �߰�,������ư ���̾�α� �̺�Ʈ���
		reset.addActionListener(this); // ��� �̺�Ʈ���
		ridCkBtn.addActionListener(this);// r_id�ߺ�üũ �̺�Ʈ ���

	}

	// �߰�/����/���� ��ɿ� ���� �κ�
	@Override
	public void actionPerformed(ActionEvent e) {
		String btnLabel = e.getActionCommand();// �̺�Ʈ��ü ���� Label ��������

		if(rejt2ob != null) {
			if (btnLabel.equals("�߰�")) {
//				if (redao2.reListInsert(this) > 0) {// ���༺��
				ArrayList<DB_ReDTO> list = redao2.ptInsertCheck(cbM_id.getSelectedItem().toString(), cbE_id.getSelectedItem().toString());
				System.out.println(list.size());
				if(list.isEmpty()) {
					if (redao2.insertre(this) > 0) {
						messageBox(this, "���� ���� �Ǿ����ϴ�");
						dispose();// JDialog â�ݱ�

//						redao2.reSelectAll(rejt2ob.redt2);// ��� ���ڵ� �����ͼ� DefaultTableModel�� �ø���
						redao2.showListadmre(rejt2ob.redt2);// ��� ���ڵ� �����ͼ� DefaultTableModel�� �ø���

						if (rejt2ob.redt2.getRowCount() > 0)
							rejt2ob.rejt2.setRowSelectionInterval(0, 0);// ù��° �� ����

					} else {
						messageBox(this, "��� �Է� ��Ź�帳�ϴ�");
					}
				} else {
					messageBox(this, "�ߺ��� �����Դϴ�.");
				}
			} else if (btnLabel.equals("����")) {
				ArrayList<DB_ReDTO> list = redao2.ptInsertCheck(cbM_id.getSelectedItem().toString(), cbE_id.getSelectedItem().toString());
				if(list.isEmpty()) {
					if (redao2.updatere(this) > 0) {
//						if (redao2.reUpdate(this) > 0) {
							messageBox(this, "�����Ǿ����ϴ�.");
							dispose();
//							redao2.reSelectAll(rejt2ob.redt2);
							redao2.showListadmre(rejt2ob.redt2);
							if (rejt2ob.redt2.getRowCount() > 0)
								rejt2ob.rejt2.setRowSelectionInterval(0, 0);
						} else {
							messageBox(this, "�������� �ʾҽ��ϴ�.");
						}
				} else {
					messageBox(this, "�ߺ��� �����Դϴ�.");
				}
				
			} else if (btnLabel.equals("���")) {
				dispose();
			} 
//			else if (btnLabel.equals("R_IDCheck")) {
//				// r_id�ؽ�Ʈ �ڽ��� �� ������ �޼��� ��� ������ DB�����Ѵ�.
//				if (r_id.getText().trim().equals("")) {
//					messageBox(this, "�����ȣ�� �Է����ּ���.");
//					r_id.requestFocus();// ��Ŀ���̵�
//				} else {
//					if (redao2.rIDCheck(r_id.getText())) { // �ߺ��ƴϴ�.(��밡��)
//						messageBox(this, r_id.getText() + "���� �߰� �����մϴ�.");
//					} else {
//						messageBox(this, r_id.getText() + "���� �ߺ� �����ȣ �Դϴ�.");
//						r_id.setText("");// text�ڽ������
//						r_id.requestFocus();// Ŀ������
//					}
//				}
//			}
		} else {
			if (btnLabel.equals("�߰�")) {
				if(cbM_id.getSelectedItem() == null || cbE_id.getSelectedItem() == null) {
					messageBox(this, "��������� Ȯ�����ּ���.");
				} else {
					ArrayList<DB_ReDTO> list = redao2.ptInsertCheck(cbM_id.getSelectedItem().toString(), cbE_id.getSelectedItem().toString());
					System.out.println(list.size());
					if(list.isEmpty()) {
						if (redao2.insertre(this) > 0) {
//							if (redao2.reListInsert(this) > 0) {// ���༺��
								messageBox(this, "���� ���� �Ǿ����ϴ�");
								dispose();// JDialog â�ݱ�

//								redao2.reSelectAll(rejt2ob2.redt2);// ��� ���ڵ� �����ͼ� DefaultTableModel�� �ø���
								redao2.showListadmre(rejt2ob2.redt2);// ��� ���ڵ� �����ͼ� DefaultTableModel�� �ø���

								if (rejt2ob2.redt2.getRowCount() > 0)
									rejt2ob2.rejt2.setRowSelectionInterval(0, 0);// ù��° �� ����

							} else {
								messageBox(this, "��� �Է� ��Ź�帳�ϴ�");
							}
					} else {
						messageBox(this, "�ߺ��� �����Դϴ�.");
					}
				}
				
			} else if (btnLabel.equals("����")) {
				if(cbM_id.getSelectedItem() == null || cbE_id.getSelectedItem() == null) {
					messageBox(this, "��������� Ȯ�����ּ���.");
				} else {
					ArrayList<DB_ReDTO> list = redao2.ptInsertCheck(cbM_id.getSelectedItem().toString(), cbE_id.getSelectedItem().toString());
					System.out.println(list.size());
					if(list.isEmpty()) {
						if (redao2.updatere(this) > 0) {
//							if (redao2.reUpdate(this) > 0) {
								messageBox(this, "�����Ǿ����ϴ�.");
								dispose();
								redao2.showListadmre(rejt2ob2.redt2);
//								redao2.reSelectAll(rejt2ob.redt2);

							} else {
								messageBox(this, "�������� �ʾҽ��ϴ�.");
							}
					} else {
						messageBox(this, "�ߺ��� �����Դϴ�.");
					}
				}
			} else if (btnLabel.equals("���")) {
				dispose();
			}
		}
		
		
	}

	// �޽����ڽ� ����ִ� �޼ҵ� �ۼ�
	public static void messageBox(Object obj, String message) {
		JOptionPane.showMessageDialog((Component) obj, message);
	}
}