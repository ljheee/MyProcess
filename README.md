# MyProcess
MyProcess,���̲鿴��

com.ljheee.os.ui    �����
com.ljheee.os.core	���ĺ�����
com.ljheee.os.model ʵ���������װ������ϸ��Ϣ
com.ljheee.os.test 	����Demo



Bug:�޷�ʵ�����������ϵ�ˢ�¡�����һ�����̺󣬽���û�ˣ��ɽ������м�¼����JTable�С�

table = new JTable();
DefaultTableModel model = new DefaultTableModel(data, title);
table.setModel(model);

model.removeRow(selectRow);//�������̣���JTable���Ƴ���