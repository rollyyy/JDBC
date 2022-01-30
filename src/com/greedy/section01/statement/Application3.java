package com.greedy.section01.statement;

import static com.greedy.common.JDBCTemplate.close;
import static com.greedy.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Application3 {

	public static void main(String[] args) {
		
		/* 1. Connection 생성 */
		Connection con = getConnection();
		
		Statement stmt = null;
		ResultSet rset = null;
		
		try {
			
			/* 2. Statement 생성 */
			stmt = con.createStatement();
			
			Scanner sc = new Scanner(System.in);
			System.out.print("조회하려는 사번을 입력해주세요 : ");
			String empId = sc.nextLine();
			String query = "SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID = '" + empId + "'";
			
			System.out.println(query);
			
			/* 3. executeQuery()로 쿼리문 실행하고 결과를 ResultSet으로 반환 받음 */
			rset = stmt.executeQuery(query);
			
			/* 4. ResultSet에 담긴 결과 값을 컬럼 이름을 이용해 꺼내오기 */
			if(rset.next()) {
				/* next() : ResultSet의 커서 위치를 하나 내리면서 행이 존재하면 true 존재하지 않으면 false를 반환 */
				System.out.println(rset.getString("EMP_ID") + ", " + rset.getString("EMP_NAME"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			/* 5. 사용한 자원 반납 */
			close(rset);
			close(stmt);
			close(con);
		}
		
	}

}

