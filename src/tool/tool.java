package tool;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.*;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;


public class tool {
	static DB_Conn_Query dbconquery;
	
	static JFrame f = new JFrame("커뮤니티 관리기 ver 1.0.0");
	static JFrame ft = new JFrame();
	static JFrame ft2 = new JFrame();
	static JPanel p = new JPanel();
	JLabel l = new JLabel("커뮤니티 관리기 ver 1.0.0");

	JLabel idL = new JLabel("아이디");
	JTextField id = new JTextField("id");
	static JButton stop=new JButton("①정지");
	static JButton list=new JButton("②작성 글");
	static JButton del=new JButton("③삭제");
	static JButton info=new JButton("④정보조회");
	
	JLabel poL = new JLabel("포인트");
	JTextField point = new JTextField("point");
	static JButton change=new JButton("⑤변경");
	static JButton addP=new JButton("⑥추가");
	static JButton delP=new JButton("⑦삭제");
	static JButton poCheck=new JButton("⑧조회");
	
	JLabel revL = new JLabel("리뷰번호");
	JTextField revNum = new JTextField("reviewNum");
	static JButton revCheck=new JButton("⑨리뷰조회");
	static JButton writer=new JButton("⑩작성자");
	static JButton revDel=new JButton("⑪리뷰삭제");
	
	JLabel keyL = new JLabel("키워드");
	static JTextField keyWord = new JTextField("keyWord");
	static JButton keyCheck=new JButton("⑫조회");
	
	static JButton report = new JButton("신고확인");

	public tool() throws SQLException{
		dbconquery = new DB_Conn_Query();
		f.setSize(1200,600);
		f.setResizable(false);
		f.setLocation(300,300);

		ft.setSize(350,600);
		ft.setResizable(false);
		ft.setLocation(500,400);
		
		ft2.setSize(350,600);
		ft2.setResizable(false);
		ft2.setLocation(500,400);
		
		p.setLayout(null);
		
		componentSet();
		eventCreate();
		
		
		
        f.addWindowListener((WindowListener) new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
        
	}
	
	
	void componentSet() {

		l.setFont(new Font("돋움", Font.BOLD, 30));
		id.setFont(new Font("돋움", Font.BOLD, 18));
		point.setFont(new Font("돋움", Font.BOLD, 18));
		revNum.setFont(new Font("돋움", Font.BOLD, 18));
		keyWord.setFont(new Font("돋움", Font.BOLD, 18));
		
		l.setBounds(400,0,700,100);
		
		idL.setBounds(20,100,50,50);
		id.setBounds(100,100,150,50);		
		stop.setBounds(300,100,150,50);
		list.setBounds(500,100,150,50);
		del.setBounds(700,100,150,50);
		info.setBounds(900,100,150,50);
		
		poL.setBounds(20,200,50,50);
		point.setBounds(100,200,150,50);		
		change.setBounds(300,200,150,50);
		addP.setBounds(500,200,150,50);
		delP.setBounds(700,200,150,50);
		poCheck.setBounds(900,200,150,50);
		
		revL.setBounds(20,300,50,50);
		revNum.setBounds(100,300,150,50);		
		revCheck.setBounds(300,300,150,50);
		writer.setBounds(500,300,150,50);
		revDel.setBounds(700,300,150,50);


		
		keyL.setBounds(20,400,50,50);
		keyWord.setBounds(100,400,150,50);		
		keyCheck.setBounds(300,400,150,50);
		
		report.setBounds(1000,500,150,50);
		
		
		p.add(l);
		p.add(idL);
		p.add(poL);
		p.add(revL);
		p.add(keyL);
		
		p.add(id);
		p.add(stop);
		p.add(list);
		p.add(del);
		p.add(info);
		p.add(point);
		p.add(change);
		p.add(addP);
		p.add(delP);
		p.add(poCheck);
		p.add(revNum);
		p.add(revCheck);
		p.add(revDel);
		p.add(writer);

		p.add(keyWord);
		p.add(keyCheck);
		
		p.add(report);
		
		f.add(p,"Center");
		f.setVisible(true);
	}
	
	
	void eventCreate() {
		stop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String temp = id.getText();
				try {
					dbconquery.ban(temp);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}	
			}
		});
		list.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String temp = id.getText();
				try {
					dbconquery.list(temp);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		del.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String temp = id.getText();
				try {
					dbconquery.del(temp);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		info.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String temp = id.getText();
				dbconquery.info(temp);
			}
		});
		change.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String temp = id.getText();
				int po = Integer.valueOf(point.getText());
				dbconquery.change(temp, po);
			}
		});
		addP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String temp = id.getText();
				int po = Integer.valueOf(point.getText());
				dbconquery.addP(temp, po);
			}
		});
		delP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String temp = id.getText();
				int po = Integer.valueOf(point.getText());
				dbconquery.delP(temp, po);
			}
		});
		poCheck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dbconquery.poCheck(id.getText());
				
			}
		});
		revCheck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dbconquery.revCheck(Integer.valueOf(revNum.getText()));
				
			}
		});
		writer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dbconquery.writer(Integer.valueOf(revNum.getText()));
			}
		});
		keyCheck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dbconquery.keyCheck(keyWord.getText());
				
			}
		});
		report.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					try {
						dbconquery.report();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
	}
	
	
	
	
	
	
	
	
	
	static class DB_Conn_Query {
		Connection con = null;
		final static String DB_URL = "jdbc:oracle:thin:@shortcut_medium?TNS_ADMIN=C:/wallet/Wallet_shortcut";
		final static String DB_USER = "admin";
		final static String DB_PASSWORD = "Rheodml123!!";
		
		
		public DB_Conn_Query() throws SQLException {
			try { 
			    Properties info = new Properties();     
			    info.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, DB_USER);
			    info.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, DB_PASSWORD);          
			    info.put(OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH, 20);    
			    

			    OracleDataSource ods;
				ods = new OracleDataSource();
				ods.setURL(DB_URL);    
				ods.setConnectionProperties(info);
				
				
			    
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	            System.out.println("드라이버 적재 성공");
	            DB_Connect();
			} catch (ClassNotFoundException e) { System.out.println("No Driver."); }
		}
		
		private void DB_Connect() {
			try { 
	            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				System.out.println("DB 연결 성공");
			} catch (SQLException e) { System.out.println("Connection Fail"); }
		}
		
		void ban(String temp) throws SQLException{ // 단순 검색
			String query = "update account set ban=1 where ID = '"+temp+"'";
			try { 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
			stmt.close(); rs.close();
			} catch (SQLException e) { 
				e.printStackTrace();
			} 
		}
		
		void report() throws SQLException{ // 단순 검색
			String query = "select title, content, time, reviewnum from report";
			try { 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				ft2.getContentPane().removeAll();
				ft2.setLayout(new GridLayout(5,1));
				while(rs.next()) {
					String title = rs.getString("title");
					String content = rs.getString("content");
					String date = rs.getString("time");
					int revNum = rs.getInt("reviewnum");
					
					JButton jt = new JButton(title);
					jt.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							listing(title,content,date, revNum);
						}
					});
					ft2.add(jt);
				}
				ft2.setVisible(true);
			stmt.close(); rs.close();
			} catch (SQLException e) { 
				e.printStackTrace();
			}
		}
		
		void list(String temp) throws SQLException{ // 단순 검색
			String query = "select title, content, time from review,account where review.nickname = account.nickname and account.ID = '"+temp+"'";
			try { 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				ft.getContentPane().removeAll();
				ft.setLayout(new GridLayout(5,1));
				while(rs.next()) {
					String title = rs.getString("title");
					String content = rs.getString("content");
					String date = rs.getString("time");
					
					
					JButton jt = new JButton(title);
					jt.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							listing(title,content,date);
						}
					});
					ft.add(jt);
				}
				ft.setVisible(true);
				
			stmt.close(); rs.close();
			} catch (SQLException e) { 
				e.printStackTrace();
			}
		}
		
		void listing(String title, String content, String date, int revNum) {
			
			JFrame li = new JFrame();
			li.setSize(300,400);
			li.setResizable(false);
			li.setLocation(500,400);
			li.setLayout(new BorderLayout());
			JPanel p = new JPanel();
			p.setLayout(new GridLayout(4,1));
			JLabel l1 = new JLabel("제목 : " + title);
			JLabel l2 = new JLabel("리뷰번호 : " + revNum);
			JLabel l3 = new JLabel("작성일 : " + date);
			JLabel l4 = new JLabel(content);

			
			
			
			p.add(l1);
			p.add(l2);
			p.add(l3);
			p.add(l4);
			li.add(p, BorderLayout.NORTH);

			li.setVisible(true);
		}
		
		void listing(String title, String content, String date) {
			
			JFrame li = new JFrame();
			li.setSize(300,400);
			li.setResizable(false);
			li.setLocation(500,400);
			JPanel p = new JPanel();

			JLabel l1 = new JLabel("제목 : " + title);
			JLabel l2 = new JLabel("작성일 : " + date);
			JLabel l3 = new JLabel(content);
			
			p.add(l1);
			p.add(l2);
			p.add(l3);
			li.add(p);

			li.setVisible(true);
		}
		
		void del(String temp) throws SQLException{ // 단순 검색
			String query = "delete from account where ID = '"+temp+"'";
			try { 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
			stmt.close(); rs.close();
			} catch (SQLException e) { 
				e.printStackTrace();
			} 
		}
		
		void info(String temp) {
			JFrame inf = new JFrame();
			JPanel p = new JPanel();
			p.setLayout(new GridLayout(12,1));
			inf.setSize(300,400);
			inf.setResizable(false);
			inf.setLocation(500,400);
			String query = "select NICKNAME, POINT FROM ACCOUNT WHERE id = '" + temp + "'";
			String query2 = "select REVIEW.REVIEWNUM FROM account, review where review.nickname = account.nickname and id = '" + temp + "'";
			
			try {
				Statement stmt  = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				while(rs.next()) {
					JLabel l1 = new JLabel("닉네임 : " + rs.getString("NICKNAME") + "\n");
					JLabel l2 = new JLabel("포인트 : " + rs.getString("POINT")+"\n");
					p.add(l1);
					p.add(l2);
				}
				
				rs = stmt.executeQuery(query2);
				String t = "작성리뷰번호 : ";
				while(rs.next()) {
					t= t + rs.getString("REVIEWNUM") + "  ";
				}
				JLabel l3 = new JLabel(t);
				p.add(l3);
				inf.add(p);
				inf.setVisible(true);
				
				stmt.close(); rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		void change(String temp, int point) {
			String query = "update account set point = " + point + " where id = '" + temp + "'";
			try {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				stmt.close(); rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		void addP(String temp, int point) {
			String query = "select point from account where id = '" + temp + "'";

			
			try {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next()) {
					point = point + rs.getInt("point");
				}
				String query2 = "update account set point = " + point + " where id = '" + temp + "'";
				rs = stmt.executeQuery(query2);
				
				stmt.close(); rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		void delP(String temp, int point) {
			String query = "select point from account where id = '" + temp + "'";
			
			
			try {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next()) {
					point = rs.getInt("point") - point;
				}
				String query2 = "update account set point = " + point + " where id = '" + temp + "'";
				rs = stmt.executeQuery(query2);
				
				stmt.close(); rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		void poCheck(String temp) {
			String query = "select point from account where id = '" + temp + "'";
			int point = 0;
			try {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next()) {
					point = rs.getInt("point");
				}
				JOptionPane.showMessageDialog(null, "포인트 : " + point);
				
				stmt.close(); rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		void revCheck(int revNum) {
			
			String query = "select title, content, time from review where reviewnum = " + revNum;
			String title,date,content;
			
			JFrame li = new JFrame();
			li.setSize(300,400);
			li.setResizable(false);
			li.setLocation(500,400);
			
			try {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next()) {
					title = rs.getString("title");
					content = rs.getString("content");
					date = rs.getString("time");
					
					JPanel p = new JPanel();
					JLabel l1 = new JLabel("제목 : " + title);
					JLabel l2 = new JLabel("작성일 : " + date);
					JLabel l3 = new JLabel(content);
					
					p.add(l1);
					p.add(l2);
					p.add(l3);
					li.add(p);
				}				
				
				stmt.close(); rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			


			li.setVisible(true);
		}
		
		void writer(int revNum) {
			String query = "select id, account.nickname from account, review where review.reviewnum = " + revNum + " and review.nickname = account.nickname";
			String nick, id;
			try {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next()) {
					id = rs.getString("id");
					nick = rs.getString("nickname");
					
					JOptionPane.showMessageDialog(null, "아이디 : " + id + "\n닉네임 : " + nick);
				}
				stmt.close(); rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		void keyCheck(String keyword) {
			String query = "select title, content, time from review where title like ?";
			keyword = '%' + keyword + '%';
			try { 
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setString(1,keyword);
				ResultSet rs = pstmt.executeQuery();
				ft.getContentPane().removeAll();
				ft.setLayout(new GridLayout(5,1));
				while(rs.next()) {
					String title = rs.getString("title");
					String content = rs.getString("content");
					String date = rs.getString("time");
					
					
					JButton jt = new JButton(title);
					jt.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							listing(title,content,date);
						}
					});
					ft.add(jt);
				}
				ft.setVisible(true);
				
			pstmt.close(); rs.close();
			} catch (SQLException e) { 
				e.printStackTrace();
			} 
		}

	}
	
	public static void main(String[] args) throws SQLException { 
		tool t = new tool();
	}
	
	
}
