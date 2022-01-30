package com.greedy.section02.preparedstatement;

import static com.greedy.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Application2 {

	public static void main(String[] args) {
		
		/* 1. Connection 생성 */
		Connection con = getConnection();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String empId = "200";
		
		try {
			
			/* 2. PreparedStatement 생성 - 쿼리문 전달하며 */
			pstmt = con.prepareStatement("SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID = ?");
			pstmt.setString(1, empId);
			
			/* 3. executeQuery()로 쿼리문 실행하고 결과를 ResultSet으로 반환 받음 */
			rset = pstmt.executeQuery();
			
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
			close(pstmt);
			close(con);
		}
		
	}

}

