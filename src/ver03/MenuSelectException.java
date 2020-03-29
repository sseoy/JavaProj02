package ver03;

public class MenuSelectException extends Exception {
	public MenuSelectException() {
		super("[예외발생] 1~5사이의 숫자를 입력하세요");
	}
}
