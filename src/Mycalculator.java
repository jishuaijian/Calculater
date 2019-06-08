import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;
import java.math.*;

import javax.management.openmbean.OpenDataException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;


public class Mycalculator extends JFrame{
	JFrame mainView;
	static JTextField jtxInput;
	JTextField jtxOutput;
	static int flag=0;
	static StringBuffer curOpnd=new StringBuffer();
	static String input="";
	Stack<String>operand=new Stack<String>();
	Stack<String>operator=new Stack<String>();
	Map<String,Integer> mymap1=new TreeMap<String, Integer>();
	Map<String,Integer> mymap2=new TreeMap<String, Integer>();
	static String first;
	static String second;
	static String result="0";
	
	//map1表示栈内元素，map2表示栈外元素
	void initMap(){
		mymap1.put("+",2);
		mymap1.put("-",2);
		mymap1.put("×",4);
		mymap1.put("÷",4);
		mymap1.put("%", 5);
		mymap1.put("∧", 7);
		mymap1.put("(",0);
		mymap1.put(")",9);
		mymap1.put("#",-1);
			
		mymap2.put("+",1);
		mymap2.put("-",1);
		mymap2.put("×",3);
		mymap2.put("÷",3);
		mymap2.put("%", 6);
		mymap2.put("∧", 8);
		mymap2.put("(",9);
		mymap2.put(")",0);
		mymap2.put("#",-1);
	}
	
	//执行进栈退栈操作
	public void Operator(String opr){	
		while(!operator.isEmpty()){
			String inner=operator.peek();	
			if(inner.equals("#")){
				break;
			}
			int value1=mymap2.get(opr);
			int value2=mymap1.get(inner);			
			if(value1>value2){
				operator.push(opr);
				break;
			}else if(value1==value2){
				operator.pop();
				break;
			}else{
				switch (inner) {
				case "+": add();break;
				case "-": sub();break;
				case "×": mul();break;
				case "÷": dev();break;
				case "∧": power();break;
				case "%": mod();break;
				default:break;
				}
				operator.pop();
			}
			
		}
		if(operator.isEmpty()&&opr!=")"){
			operator.push(opr);
		}
	};
	
	//定义逻辑操作
	String fibo(String ss){
		double temp=Double.parseDouble(ss);
		double re=1;
		while(temp>0){
			re*=temp;
			temp--;
		}
		return String.valueOf(re);
	}
//	String fibo(String ss){
//		double temp=Double.parseDouble(ss);
//		BigDecimal re=new BigDecimal(1,new MathContext(32, RoundingMode.HALF_UP));
//		while(temp>0){
//			re=re.multiply(new BigDecimal(temp));
//			temp--;
//		}
//		return re.toString();
//	}
	void add(){
		first=operand.pop();
		second=operand.pop();
		System.out.println(first+" "+second);
		double re=Double.parseDouble(first)+Double.parseDouble(second);
		result=String.valueOf(re);
		operand.push(result);
		System.out.println(operand);
		jtxOutput.setText(result);
	}
	void sub(){
		first=operand.pop();
		second=operand.pop();
		double re=Double.parseDouble(second)-Double.parseDouble(first);
		result=String.valueOf(re);
		operand.push(result);
		System.out.println(operand);
		jtxOutput.setText(result);
	}
	void mul(){
		first=operand.pop();
		second=operand.pop();
		double re=Double.parseDouble(first)*Double.parseDouble(second);
		result=String.valueOf(re);
		operand.push(result);
		System.out.println(operand);
		jtxOutput.setText(result);
	}
	void dev(){
		first=operand.pop();
		second=operand.pop();
		double re=Double.parseDouble(second)/Double.parseDouble(first);
		result=String.valueOf(re);
		operand.push(result);
		System.out.println(operand);
		jtxOutput.setText(result);
	}
	void power(){
		first=operand.pop();
		second=operand.pop();
		double re=Math.pow(Double.parseDouble(second),Double.parseDouble(first) );
		result=String.valueOf(re);
		operand.push(result);
		System.out.println(operand);
		jtxOutput.setText(result);
	}
	void mod(){
		first=operand.pop();
		second=operand.pop();
		double re=Math.floorMod(Long.parseLong(second),Long.parseLong(first) ) ;
		result=String.valueOf(re);
		operand.push(result);
		System.out.println(operand);
		jtxOutput.setText(result);
	}
	public Mycalculator(){	
			initMap();
			mainView= new JFrame("计算器");
			mainView.setBounds(500, 150, 700, 800);
			mainView.setLayout(null);
			
			//设置输入输出框
			Font ff=new Font("宋体",Font.BOLD,25);		
			jtxInput=new JTextField(32);
			jtxInput.setBackground(Color.white);
			jtxInput.setBounds(0, 0, 700,50);			
			jtxOutput=new JTextField(32);
			jtxOutput.setBackground(Color.white);
			jtxOutput.setBounds(0, 55, 700,50);
			jtxInput.setFont(ff);
			jtxOutput.setFont(ff);
			
			//设置操作框
			Font f=new Font("宋体",Font.BOLD,30);//根据指定字体名称、样式和磅值大小，创建一个新 Font
			JButton button[][]=new JButton[7][5];
			int x=0,y=110,width=130,height=90;		
			for(int i=0;i<7;i++){						
				for(int j=0;j<5;j++){
					button[i][j]=new JButton();
					button[i][j].setBackground(Color.gray);
					button[i][j].setFont(f);
					button[i][j].setBounds(x,y,width,height);
					x+=135;
					mainView.add(button[i][j]);
				}
				x=0;
				y+=95;
			}
			button[0][0].setLabel("x²");
			button[0][1].setLabel("∧");
			button[0][2].setLabel("sin");
			button[0][3].setLabel("cos");
			button[0][4].setLabel("tan");
			
			button[1][0].setLabel("√");
			button[1][1].setLabel("10∧");
			button[1][2].setLabel("log");
			button[1][3].setLabel("Exp");
			button[1][4].setLabel("Mod");
			
			button[2][0].setLabel("↑");
			button[2][1].setLabel("CE");
			button[2][2].setLabel("C");
			button[2][3].setLabel("del");
			button[2][4].setLabel("÷");
			
			button[3][0].setLabel("π");
			button[3][1].setLabel("7");
			button[3][2].setLabel("8");
			button[3][3].setLabel("9");
			button[3][4].setLabel("×");
			
			button[4][0].setText("n!");
			button[4][1].setText("4");
			button[4][2].setText("5");
			button[4][3].setText("6");
			button[4][4].setText("-");
			
			button[5][0].setText("±");
			button[5][1].setText("1");
			button[5][2].setText("2");
			button[5][3].setText("3");
			button[5][4].setText("+");
			
			button[6][0].setText("(");
			button[6][1].setText(")");
			button[6][2].setText("0");
			button[6][3].setText(".");
			button[6][4].setText("=");	
			
			//设置数字按钮监听事件
			button[3][1].addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
					if(flag==0){
						curOpnd.append("7");
					}else{
						flag=0;
						curOpnd.delete(0, curOpnd.length());
						curOpnd.append("7");
					}
					jtxOutput.setText(curOpnd.toString());
				}
			});
			button[3][2].addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
					if(flag==0){
						curOpnd.append("8");
						System.out.println(curOpnd);
					}else{
						flag=0;
						curOpnd.delete(0, curOpnd.length());
						System.out.println(curOpnd);
						curOpnd.append("8");
					}
					jtxOutput.setText(curOpnd.toString());
				}
			});
			button[3][3].addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
					if(flag==0){
						curOpnd.append("9");
					}else{
						flag=0;
						curOpnd.delete(0, curOpnd.length());
						curOpnd.append("9");
					}
					jtxOutput.setText(curOpnd.toString());
				}
			});
			
			button[4][1].addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
					if(flag==0){
						curOpnd.append("4");
					}else{
						flag=0;
						curOpnd.delete(0, curOpnd.length());
						curOpnd.append("4");
					}
					jtxOutput.setText(curOpnd.toString());
				}
			});
			button[4][2].addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
					if(flag==0){
						curOpnd.append("5");
					}else{
						flag=0;
						curOpnd.delete(0, curOpnd.length());
						curOpnd.append("5");
					}
					jtxOutput.setText(curOpnd.toString());
				}
			});
			button[4][3].addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
					if(flag==0){
						System.out.println(".....");
						curOpnd.append("6");
					}else{
						flag=0;
						curOpnd.delete(0, curOpnd.length());
						System.out.print(curOpnd);
						curOpnd.append("6");
						System.out.println(curOpnd);
					}
					jtxOutput.setText(curOpnd.toString());
				}
			});
			
			button[5][1].addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
					if(flag==0){
						curOpnd.append("1");
					}else{
						flag=0;
						curOpnd.delete(0, curOpnd.length());
						curOpnd.append("1");
					}
					jtxOutput.setText(curOpnd.toString());
				}
			});
			button[5][2].addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
					if(flag==0){
						curOpnd.append("2");
					}else{
						flag=0;
						curOpnd.delete(0, curOpnd.length());
						curOpnd.append("2");
					}
					jtxOutput.setText(curOpnd.toString());
				}
			});
			button[5][3].addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
					if(flag==0){
						curOpnd.append("3");
					}else{
						flag=0;
						curOpnd.delete(0, curOpnd.length());
						curOpnd.append("3");
					}	
					jtxOutput.setText(curOpnd.toString());
				}
			});
			
			button[6][2].addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
					if(flag==0){
						curOpnd.append("0");
					}else{
						flag=0;
						curOpnd.delete(0, curOpnd.length());
						curOpnd.append("0");
					}	
					jtxOutput.setText(curOpnd.toString());
				}
			});
			button[6][3].addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
					if(flag==0){
						curOpnd.append(".");
					}else{
						flag=0;
						curOpnd.delete(0, curOpnd.length());
						curOpnd.append(".");
					}	
					jtxOutput.setText(curOpnd.toString());
				}
			});
			
			//设置操作符按钮监听事件,基础操作符，加减乘除、括号、∧
			button[2][4].addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
						if(flag==0){
							if(!curOpnd.toString().equals("")){
								operand.push(curOpnd.toString());
							}
							result=curOpnd.toString();
						}
						flag=1;
						if(operator.isEmpty()){
							operator.push("÷");
						}else{							
							System.out.println(curOpnd);
							Operator("÷");
						}
						input+=curOpnd.toString();
						input+="÷";
						jtxInput.setText(input);
						curOpnd.delete(0, curOpnd.length());
						System.out.println(operand);									
				}
			
			});
			
			button[3][4].addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
						if(flag==0){
							if(!curOpnd.toString().equals("")){
								operand.push(curOpnd.toString());
								result=curOpnd.toString();
							}
							
						}
						flag=1;
						if(operator.isEmpty()){
							operator.push("×");
						}else{							
							System.out.println(curOpnd);
							Operator("×");
						}			
						input+=curOpnd.toString();
						input+="×";
						jtxInput.setText(input);
						curOpnd.delete(0, curOpnd.length());
						System.out.println(operand);
										
				}
			});
			button[4][4].addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
						
						if(flag==0){
							if(!curOpnd.toString().equals("")){
								operand.push(curOpnd.toString());
								result=curOpnd.toString();
							}
							
						}
						flag=1;
						if(operator.isEmpty()){
							operator.push("-");
						}else{							
							System.out.println(curOpnd);
							Operator("-");
						}
						input+=curOpnd.toString();
						input+="-";
						jtxInput.setText(input);
						curOpnd.delete(0, curOpnd.length());
						System.out.println(operand);			
				}
			});
			button[5][4].addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
						if(flag==0){
							if(!curOpnd.toString().equals("")){
								operand.push(curOpnd.toString());
								result=curOpnd.toString();
							}
						}
						flag=1;
						if(operator.isEmpty()){
							operator.push("+");
						}else{
							Operator("+");
						}
						input+=curOpnd.toString();
						input+="+";
						jtxInput.setText(input);
						
						curOpnd.delete(0, curOpnd.length());
						System.out.println(operand);
										
				}
			});
			//设置∧
			button[0][1].addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
						if(flag==0){
							if(!curOpnd.toString().equals("")){
								operand.push(curOpnd.toString());
							}
							result=curOpnd.toString();
						}
						flag=1;
						if(operator.isEmpty()){
							operator.push("∧");
						}else{							
							System.out.println(curOpnd);
							Operator("∧");
						}
						input+=curOpnd.toString();
						input+="∧";
						jtxInput.setText(input);
						curOpnd.delete(0, curOpnd.length());
						System.out.println(operand);
						System.out.println(operator);
										
				}
			
			});
			
			//设置Mod
			button[1][4].addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
						if(flag==0){
							if(!curOpnd.toString().equals("")){
								operand.push(curOpnd.toString());
							}
							result=curOpnd.toString();
						}
						flag=1;
						if(operator.isEmpty()){
							operator.push("%");
						}else{							
							System.out.println(curOpnd);
							Operator("%");
						}
						input+=curOpnd.toString();
						input+="%";
						jtxInput.setText(input);
						curOpnd.delete(0, curOpnd.length());
						System.out.println(operand);
						System.out.println(operator);
										
				}
			
			});
			
			button[6][0].addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
						if(flag==0){
							if(!curOpnd.toString().equals("")){
								operand.push(curOpnd.toString());
								result=curOpnd.toString();
							}
						}
						flag=1;
						if(operator.isEmpty()){
							operator.push("(");
						}else{
							Operator("(");
						}
						input+=curOpnd.toString();
						input+="(";
						jtxInput.setText(input);
						curOpnd.delete(0, curOpnd.length());
						System.out.println(operand);
										
				}
			});
			button[6][1].addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
						if(flag==0){
							if(!curOpnd.toString().equals("")){
								operand.push(curOpnd.toString());
								result=curOpnd.toString();
							}							
						}
						flag=1;
						if(operator.isEmpty()){
							operator.push(")");
						}else{
							Operator(")");
						}
						input+=curOpnd.toString();
						input+=")";
						jtxInput.setText(input);
						curOpnd.delete(0, curOpnd.length());
						System.out.println(operand);
										
				}
			});
			
			//设置高级操作符，平方及三角函数
			button[0][0].addActionListener(new ActionListener() {					
				@Override
				public void actionPerformed(ActionEvent e) {
						if(flag==0){
							String top;
							if(!curOpnd.toString().equals("")){
								input+="sqrt("+curOpnd+")";
								jtxInput.setText(input);
								top=curOpnd.toString();
							}else{
								input+="sqrt("+result+")";
								jtxInput.setText(input);
								top=operand.pop();
							}
							
							double temp=Double.parseDouble(top);
							temp*=temp;
							operand.push(String.valueOf(temp));
							result=String.valueOf(temp);
							jtxOutput.setText(String.valueOf(temp));
							curOpnd.delete(0,curOpnd.length());
						}else{
							System.out.println("????"+result);
							input+="sqrt("+result+")";
							jtxInput.setText(input);
							double temp=Double.parseDouble(result);
							temp*=temp;
							operand.push(String.valueOf(temp));
							result=String.valueOf(temp);
							jtxOutput.setText(String.valueOf(temp));
							curOpnd.delete(0,curOpnd.length());
							System.out.println(result+" ??");
						}	
						System.out.println(operand);
				}
			});
			
			button[0][2].addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
						if(flag==0){
							String top;
							if(!curOpnd.toString().equals("")){
								input+="sin("+curOpnd+")";
								jtxInput.setText(input);
								top=curOpnd.toString();
								
							}else{
								input+="sin("+result+")";
								jtxInput.setText(input);
								top=operand.pop();
							}
							
							double temp=Double.parseDouble(top);							
							temp= Math.toRadians(temp);//把数字90 转换成 90度
							temp=Math.sin(temp);							
							operand.push(String.valueOf(temp));
							result=String.valueOf(temp);
							jtxOutput.setText(String.valueOf(temp));	
							curOpnd.delete(0,curOpnd.length());
						}else{
							input+="sin("+result+")";
							jtxInput.setText(input);
							double temp=Double.parseDouble(result);							
							temp= Math.toRadians(temp);//把数字90 转换成 90度
							temp=Math.sin(temp);	
							operand.push(String.valueOf(temp));
							result=String.valueOf(temp);
							jtxOutput.setText(String.valueOf(temp));
						}	
						System.out.println(operand);
						System.out.println("reslt:"+result);
				}
			});
			
			button[0][3].addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
						if(flag==0){
							String top;
							if(!curOpnd.toString().equals("")){
								input+="cos("+curOpnd+")";
								jtxInput.setText(input);
								top=curOpnd.toString();
								
							}else{
								input+="cos("+result+")";
								jtxInput.setText(input);
								top=operand.pop();
							}
							
							double temp=Double.parseDouble(top);							
							temp= Math.toRadians(temp);//把数字90 转换成 90度
							temp=Math.cos(temp);							
							operand.push(String.valueOf(temp));
							result=String.valueOf(temp);
							jtxOutput.setText(String.valueOf(temp));	
							curOpnd.delete(0,curOpnd.length());
						}else{
							input+="cos("+result+")";
							jtxInput.setText(input);
							double temp=Double.parseDouble(result);							
							temp= Math.toRadians(temp);//把数字90 转换成 90度
							temp=Math.cos(temp);	
							operand.push(String.valueOf(temp));
							result=String.valueOf(temp);
							jtxOutput.setText(String.valueOf(temp));
						}	
						System.out.println(operand);
						System.out.println("reslt:"+result);
				}
			});
			
			button[0][4].addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
						if(flag==0){
							String top;
							if(!curOpnd.toString().equals("")){
								input+="tan("+curOpnd+")";
								jtxInput.setText(input);
								top=curOpnd.toString();
								
							}else{
								input+="tan("+result+")";
								jtxInput.setText(input);
								top=operand.pop();
							}
							
							double temp=Double.parseDouble(top);							
							temp= Math.toRadians(temp);//把数字90 转换成 90度
							temp=Math.tan(temp);							
							operand.push(String.valueOf(temp));
							result=String.valueOf(temp);
							jtxOutput.setText(String.valueOf(temp));	
							curOpnd.delete(0,curOpnd.length());
						}else{
							input+="tan("+result+")";
							jtxInput.setText(input);
							double temp=Double.parseDouble(result);							
							temp= Math.toRadians(temp);//把数字90 转换成 90度
							temp=Math.tan(temp);	
							operand.push(String.valueOf(temp));
							result=String.valueOf(temp);
							jtxOutput.setText(String.valueOf(temp));
						}	
						System.out.println(operand);
						System.out.println("reslt:"+result);
				}
			});
			//根号
			button[1][0].addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
						if(flag==0){
							String top;
							if(!curOpnd.toString().equals("")){
								input+="√("+curOpnd+")";
								jtxInput.setText(input);
								top=curOpnd.toString();
								
							}else{
								input+="√("+result+")";
								jtxInput.setText(input);
								top=operand.pop();
							}
							
							double temp=Double.parseDouble(top);							
							temp= Math.pow(temp, 0.5);	
							operand.push(String.valueOf(temp));
							result=String.valueOf(temp);
							jtxOutput.setText(String.valueOf(temp));	
							curOpnd.delete(0,curOpnd.length());
						}else{
							input+="√("+result+")";
							jtxInput.setText(input);
							double temp=Double.parseDouble(result);							
							temp= Math.pow(temp, 0.5);	
							operand.push(String.valueOf(temp));
							result=String.valueOf(temp);
							jtxOutput.setText(String.valueOf(temp));
						}	
						System.out.println(operand);
						System.out.println("reslt:"+result);
				}
			});
			//log函数
			button[1][2].addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
						if(flag==0){
							String top;
							if(!curOpnd.toString().equals("")){
								input+="log("+curOpnd+")";
								jtxInput.setText(input);
								top=curOpnd.toString();
								
							}else{
								input+="log("+result+")";
								jtxInput.setText(input);
								top=operand.pop();
							}
							double temp=Double.parseDouble(top);							
							temp=Math.log10(temp);							
							operand.push(String.valueOf(temp));
							result=String.valueOf(temp);
							jtxOutput.setText(String.valueOf(temp));	
							curOpnd.delete(0,curOpnd.length());
						}else{
							input+="log("+result+")";
							jtxInput.setText(input);
							double temp=Double.parseDouble(result);							
							temp=Math.log10(temp);
							operand.push(String.valueOf(temp));
							result=String.valueOf(temp);
							jtxOutput.setText(String.valueOf(temp));
						}	
						System.out.println(operand);
						System.out.println("reslt:"+result);
				}
			});
			
			//10的幂函数
			button[1][1].addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println(">?>?>?>");
						if(flag==0){
							String top;
							if(!curOpnd.toString().equals("")){
								input+="10∧("+curOpnd+")";
								jtxInput.setText(input);
								top=curOpnd.toString();
								
							}else{
								input+="10∧("+result+")";
								jtxInput.setText(input);
								top=operand.pop();
							}
							double temp=Double.parseDouble(top);							
							temp=Math.pow(10, temp);
							operand.push(String.valueOf(temp));
							result=String.valueOf(temp);
							jtxOutput.setText(String.valueOf(temp));	
							curOpnd.delete(0,curOpnd.length());
						}else{
							input+="10∧("+result+")";
							jtxInput.setText(input);
							double temp=Double.parseDouble(result);							
							temp=Math.pow(10, temp);
							operand.push(String.valueOf(temp));
							result=String.valueOf(temp);
							jtxOutput.setText(String.valueOf(temp));
						}	
						System.out.println(operand);
						System.out.println("reslt:"+result);
				}
			});
			
			//阶乘
			button[4][0].addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
						if(flag==0){
							String top;
							if(!curOpnd.toString().equals("")){
								input+="fact("+curOpnd+")";
								jtxInput.setText(input);
								top=curOpnd.toString();
								
							}else{
								input+="fact("+result+")";
								jtxInput.setText(input);
								top=operand.pop();
							}
							System.out.println(top);
							String temp=fibo(top);
							System.out.println(temp);
							operand.push(temp);
							result=temp;
							jtxOutput.setText(temp);	
							curOpnd.delete(0,curOpnd.length());
						}else{
							input+="fact("+result+")";
							jtxInput.setText(input);
							String temp=fibo(result);
							operand.push(temp);
							result=temp;
							jtxOutput.setText(temp);							}	
						System.out.println(operand);
						System.out.println("reslt:"+result);
				}
			});
			
			//设置取反
			button[5][0].addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
						if(flag==0){
							String top;
							if(!curOpnd.toString().equals("")){
								input+="-("+curOpnd+")";
								jtxInput.setText(input);
								top=curOpnd.toString();
								
							}else{
								input+="neg("+result+")";
								jtxInput.setText(input);
								top=operand.pop();
							}
							
							String temp;				
							if(top.charAt(0)=='-'){
								System.out.println(",./,.,.<>?<?");
								temp=(String)top.subSequence(1, top.length());
							}else{
								temp="-"+top;
							}
							operand.push(temp);
							result=temp;
							jtxOutput.setText(temp);	
							curOpnd.delete(0,curOpnd.length());
						}else{
							input+="neg("+result+")";
							jtxInput.setText(input);
							String temp;
							if(result.charAt(0)=='-'){
								temp=(String)result.subSequence(1, result.length()-1);
							}else{
								temp="-"+result;
							}
							operand.push(temp);
							result=temp;
							jtxOutput.setText(temp);	
						}	
						System.out.println(operand);
						System.out.println("reslt:"+result);
				}
			});
			
			
			//
			//设置清空操作,C代表全部清空，计算重新开始，CE表示清空当前输入的数字，DEL表示删除当前输入的第一个数字
			button[2][2].addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
						
						operand.clear();
						operator.clear();
						jtxInput.setText("");
						jtxOutput.setText("");
						input="";
						flag=0;
						curOpnd.delete(0, curOpnd.length());
						result="0";
				}
			});
			
			button[2][1].addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
					curOpnd.delete(0, curOpnd.length());
					jtxOutput.setText("");
				}
			});
			
			button[2][3].addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
					curOpnd.delete(0, 1);
					jtxOutput.setText(curOpnd.toString());
				}
			});
			
			//设置等于
			button[6][4].addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
					    System.out.println(operand+">>>???"+operator);
						if(!curOpnd.toString().equals("")){
							operand.push(curOpnd.toString());
						}		
						System.out.println(operand+">>>???"+operator);
					    Operator("#");
					    
						operand.clear();
						operator.clear();
						jtxInput.setText("");
						jtxOutput.setText(result);
						input="";
						flag=0;
						curOpnd.delete(0, curOpnd.length());
						result="0";
				}
			});
			//设置圆周率
			button[3][0].addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
					if(flag==0){
						curOpnd.delete(0, curOpnd.length());
						double re=Math.PI;
						operand.push(String.valueOf(re));
						result=String.valueOf(re);
					}else{
						flag=0;
						curOpnd.delete(0, curOpnd.length());
						double re=Math.PI;
						operand.push(String.valueOf(re));
						result=String.valueOf(re);
					}
					input+="π";
					jtxInput.setText(input);
					jtxOutput.setText(result);
				}
			});
		
			//设置主窗口属性
			mainView.add(jtxInput);
			mainView.add(jtxOutput);		
			mainView.setBackground(Color.white);
			mainView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			mainView.setVisible(true);
	}			

	public static void main(String[] args) {
		new Mycalculator();
		
	}
}
