package com.spring.basic.board.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
	
	private int boardNo;
	private String writer;
	private String title;
	private String content;
	private LocalDateTime regDate;
	
	
	/*
	 * 자바의 빌더 패턴 (builder pattern)
	 * 객체를 생성하는 방법을 정의하는 내부 클래스
	 * 기존에는 생성자 혹은 setter 메서드를 통해 객체의 필드를 직접 초기화를 했다면
	 * builder 클래스를 디자인하고 활용하여 원하는 필드만 빠르게 초기화 할 수 있고,
	 * 코드 가독성이 좋아 질 수 있음
	 

	
	//1. 중첩 static class를 이용하여 기존 객체와 동일한 모양의 클래스를 하나 더 구축함.
	public static class BoardBulider{
		//기존 객체와 동일한 필드를 가짐
		private int boardNo;
		private String writer;
		private String title;
		private String content;
		private LocalDateTime regDate;
		
		//2. 내부 클래스의 생성자를 private로 제한해서
		// Board 클래스 밖에서는 호출할 수 없도록 지정.
		private BoardBulider() {}
		
		
		
		//3. 필드 별 setter 메서드를 구축해줌
		//return type은 BoardBuilder 객체를 리턴하게끔 작성함.
		public BoardBulider boardNo(int boardNo) {
			this.boardNo = boardNo;
			return this;
		}
		
		public BoardBulider writer(String writer) {
			this.writer = writer;
			return this;
		}
		
		public BoardBulider title(String title) {
			this.title = title;
			return this;
		}
		
		public BoardBulider content(String content) {
			this.content = content;
			return this;
		}
		
		public BoardBulider regDate(LocalDateTime regDate) {
			this.regDate = regDate;
			return this;
		}
		
		//4. 빌더 완료 메서드를 선언함.
		public Board build() {
			return new Board(boardNo,writer,title,content,regDate);
		}
	} //end boardBuilder class
	
	
	//5. 빌더 호출 메서드(Board가 BoardBuilder를 호출하는 메서드)
	public static BoardBulider builder() {
		return new BoardBulider();
	}
	*/
}
