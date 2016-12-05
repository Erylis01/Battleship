package controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;

import javax.swing.JTextArea;

import model.BoardPlayer;
import model.Ship;
import view.Draughtboard;

public class GameController {

	private BoardPlayer boardPlayer;
	private Draughtboard draughtBoard;
	private Ship carrier = new Ship(5);
	private Ship battleship = new Ship(4);
	private Ship cruiser = new Ship(3);
	private Ship submarine = new Ship(3);
	private Ship destroyer = new Ship(2);
	boolean inRow = false;
	boolean inCol = false;
	private int size = 0;
	private String empty = "empty";

	public GameController(BoardPlayer boardPlayer) {
		this.setBoardPlayer(boardPlayer);
		setDraughtBoard(new Draughtboard(this));
	}

	public void shipsPlacement(JTextArea console, int col, int row) {
		if (size < 5) {
			console.setText("Placer votre porte-avions (5 cases)");
			carrierPlacement(col, row);
		} else if (size >= 5 && size < 9) {
			console.setText("Placer votre croiseur (4 cases)");
			battleshipPlacement(col, row);
		} else if (size >= 9 && size < 12) {
			console.setText("Placer votre contre-torpilleurs (3 cases)");
			cruiserPlacement(col, row);
		} else if (size >= 12 && size < 15) {
			console.setText("Placer votre sous-marin (3 cases)");
			submarinePlacement(col, row);
		} else if (size >= 15 && size < 17) {
			console.setText("Placer votre torpilleur (2 cases)");
			destroyerPlacement(col, row);
		}
	}

	public void carrierPlacement(int col, int row) {

		if (empty.equals(boardPlayer.getBoard()[row][col]) && size == 0) {
			updateOneCell(carrier, row, col);
			size++;
		} else if (empty.equals(boardPlayer.getBoard()[row][col]) && size == 1) {
			if ((carrier.getShip()[0][0].equals(Integer.toString(row + 1))
					|| carrier.getShip()[0][0].equals(Integer.toString(row - 1)))
					&& carrier.getShip()[0][1].equals(Integer.toString(col))) {
				inCol = true;
				updateOneCell(carrier, row, col);
			} else if ((carrier.getShip()[0][1].equals(Integer.toString(col + 1))
					|| carrier.getShip()[0][1].equals(Integer.toString(col - 1)))
					&& carrier.getShip()[0][0].equals(Integer.toString(row))) {
				inRow = true;
				updateOneCell(carrier, row, col);
			}
			size++;
		} else if ((empty.equals(boardPlayer.getBoard()[row][col])) && size >= 2) {
			if (inCol && !inRow) {
				if (boardPlayer.adjacentBoxColumn(carrier, col, row)) {
					updateOneCell(carrier, row, col);
					size++;
				}
			} else if (inRow && !inCol) {
				if (boardPlayer.adjacentBoxRow(carrier, col, row)) {
					updateOneCell(carrier, row, col);
					size++;
				}
			}
		}
	}

	public void battleshipPlacement(int col, int row) {
		if (empty.equals(boardPlayer.getBoard()[row][col]) && size == 5) {
			updateOneCell(battleship, row, col);
			inRow=inCol=false;
			size++;
		} else if (empty.equals(boardPlayer.getBoard()[row][col]) && size == 6) {
			if ((battleship.getShip()[0][0].equals(Integer.toString(row + 1))
					|| battleship.getShip()[0][0].equals(Integer.toString(row - 1)))
					&& battleship.getShip()[0][1].equals(Integer.toString(col))) {
				inCol = true;
				updateOneCell(battleship, row, col);
			} else if ((battleship.getShip()[0][1].equals(Integer.toString(col + 1))
					|| battleship.getShip()[0][1].equals(Integer.toString(col - 1)))
					&& battleship.getShip()[0][0].equals(Integer.toString(row))) {
				inRow = true;
				updateOneCell(battleship, row, col);
			}
			size++;
		} else if ((empty.equals(boardPlayer.getBoard()[row][col])) && size >= 7) {
			if (inCol && !inRow) {
				if (boardPlayer.adjacentBoxColumn(battleship, col, row)) {
					updateOneCell(battleship, row, col);
					size++;
				}
			} else if (inRow && !inCol) {
				if (boardPlayer.adjacentBoxRow(battleship, col, row)) {
					updateOneCell(battleship, row, col);
					size++;
				}
			}
		}
	}

	public void cruiserPlacement(int col, int row) {
		if (empty.equals(boardPlayer.getBoard()[row][col]) && size == 9) {
			updateOneCell(cruiser, row, col);
			inRow=inCol=false;
			size++;
		} else if (empty.equals(boardPlayer.getBoard()[row][col]) && size == 10) {
			if ((cruiser.getShip()[0][0].equals(Integer.toString(row + 1))
					|| cruiser.getShip()[0][0].equals(Integer.toString(row - 1)))
					&& cruiser.getShip()[0][1].equals(Integer.toString(col))) {
				inCol = true;
				updateOneCell(cruiser, row, col);
			} else if ((cruiser.getShip()[0][1].equals(Integer.toString(col + 1))
					|| cruiser.getShip()[0][1].equals(Integer.toString(col - 1)))
					&& cruiser.getShip()[0][0].equals(Integer.toString(row))) {
				inRow = true;
				updateOneCell(cruiser, row, col);
			}
			size++;
		} else if ((empty.equals(boardPlayer.getBoard()[row][col])) && size >= 11) {
			if (inCol && !inRow) {
				if (boardPlayer.adjacentBoxColumn(cruiser, col, row)) {
					updateOneCell(cruiser, row, col);
					size++;
				}
			} else if (inRow && !inCol) {
				if (boardPlayer.adjacentBoxRow(cruiser, col, row)) {
					updateOneCell(cruiser, row, col);
					size++;
				}
			}
		}
	}

	public void submarinePlacement(int row, int col) {
		if (empty.equals(boardPlayer.getBoard()[row][col]) && size == 12) {
			updateOneCell(submarine, row, col);
			inRow=inCol=false;
			size++;
		} else if (empty.equals(boardPlayer.getBoard()[row][col]) && size == 13) {
			if ((submarine.getShip()[0][0].equals(Integer.toString(row + 1))
					|| submarine.getShip()[0][0].equals(Integer.toString(row - 1)))
					&& submarine.getShip()[0][1].equals(Integer.toString(col))) {
				inCol = true;
				updateOneCell(submarine, row, col);
			} else if ((submarine.getShip()[0][1].equals(Integer.toString(col + 1))
					|| submarine.getShip()[0][1].equals(Integer.toString(col - 1)))
					&& submarine.getShip()[0][0].equals(Integer.toString(row))) {
				inRow = true;
				updateOneCell(submarine, row, col);
			}
			size++;
		} else if ((empty.equals(boardPlayer.getBoard()[row][col])) && size >= 14) {
			if (inCol && !inRow) {
				if (boardPlayer.adjacentBoxColumn(submarine, col, row)) {
					updateOneCell(submarine, row, col);
					size++;
				}
			} else if (inRow && !inCol) {
				if (boardPlayer.adjacentBoxRow(submarine, col, row)) {
					updateOneCell(submarine, row, col);
					size++;
				}
			}
		}
	}

	public void destroyerPlacement(int row, int col) {
		if (empty.equals(boardPlayer.getBoard()[row][col]) && size == 15) {
			updateOneCell(destroyer, row, col);
			inRow=inCol=false;
			size++;
		} else if (empty.equals(boardPlayer.getBoard()[row][col]) && size == 16) {
			if ((destroyer.getShip()[0][0].equals(Integer.toString(row + 1))
					|| destroyer.getShip()[0][0].equals(Integer.toString(row - 1)))
					&& destroyer.getShip()[0][1].equals(Integer.toString(col))) {
				inCol = true;
				updateOneCell(destroyer, row, col);
			} else if ((destroyer.getShip()[0][1].equals(Integer.toString(col + 1))
					|| destroyer.getShip()[0][1].equals(Integer.toString(col - 1)))
					&& destroyer.getShip()[0][0].equals(Integer.toString(row))) {
				inRow = true;
				updateOneCell(destroyer, row, col);
			}
			size++;
		}
	}


	public void updateOneCell(Ship ship, int row, int col) {
		boardPlayer.updateBoard(row, col, "full");
		if (ship.equals(carrier)){
			ship.updateShip(size, row, col, "safe");
		}else if (ship.equals(battleship)){
			ship.updateShip(size-5, row, col, "safe");
		}else if (ship.equals(cruiser)){
			ship.updateShip(size-9, row, col, "safe");
		}else if (ship.equals(submarine)){
			ship.updateShip(size-12, row, col, "safe");
		}else if (ship.equals(destroyer)){
			ship.updateShip(size-15, row, col, "safe");
		}
		Component c = draughtBoard.getBoardPlayer().getComponentAt(col * 34, row * 34);
		c.setBackground(Color.blue);
	}

	public BoardPlayer getBoardPlayer() {
		return boardPlayer;
	}

	public void setBoardPlayer(BoardPlayer boardPlayer) {
		this.boardPlayer = boardPlayer;
	}

	public Draughtboard getDraughtBoard() {
		return draughtBoard;
	}

	public void setDraughtBoard(Draughtboard draughtBoard) {
		this.draughtBoard = draughtBoard;
	}
}
