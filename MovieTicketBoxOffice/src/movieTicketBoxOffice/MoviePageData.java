package movieTicketBoxOffice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;

import pageManageSystem.PageData;

class Movie {
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getGenre() {
		return genre;
	}
	public String getRating() {
		return rating;
	}
	public String getDirector() {
		return director;
	}
	public String getActor() {
		return actor;
	}
	public int getTime() {
		return time;
	}
	@Override
	public String toString() {
		return "Movie [id=" + id + ", name=" + name + ", genre=" + genre
				+ ", rating=" + rating + ", director=" + director + ", actor="
				+ actor + ", time=" + time + "]";
	}
	protected int id;
	protected String name;
	protected String genre;
	protected String rating;
	protected String director;
	protected String actor;
	protected int time;
	public Movie() {
		this(0,"default","default","default","default","default",0);
	}
	public Movie(int id, String name, String genre, String rating, String director, String actor, int time) {
		super();
		this.id = id;
		this.name = name;
		this.genre = genre;
		this.rating = rating;
		this.director = director;
		this.actor = actor;
		this.time = time;
	}
}

class ScheduleReservation {
	public int getScheduleId() {
		return scheduleId;
	}
	public int getSeatCount() {
		return seatCount;
	}
	public int[] getRow() {
		return row;
	}
	public int[] getCol() {
		return col;
	}
	protected int scheduleId;
	protected int seatCount;
	protected int [] row;
	protected int [] col;
	public ScheduleReservation(int scheduleId, int seatCount) {
		super();
		this.scheduleId = scheduleId;
		this.seatCount = seatCount;
		if(seatCount != 0) {
			row = new int[seatCount];
			col = new int[seatCount];
		}
	}
	public void addSeat(int idx, int _row, int _col) {
		if(idx < 0 || idx >= seatCount) {
			return;
		}
		row[idx] = _row;
		col[idx] = _col;
	}
}

class Schedule {
	public int getId() {
		return id;
	}
	public Date getDate() {
		return date;
	}
	public Time getTime() {
		return time;
	}
	public int getPrice() {
		return price;
	}
	public int getMovie_id() {
		return movie_id;
	}
	public int getScreen_id() {
		return screen_id;
	}
	protected int id;
	protected Date date;
	protected Time time;
	protected int price;
	protected int movie_id;
	protected int screen_id;
	public Schedule(int id, Date date, Time time, int price, int movie_id,
			int screen_id) {
		super();
		this.id = id;
		this.date = date;
		this.time = time;
		this.price = price;
		this.movie_id = movie_id;
		this.screen_id = screen_id;
	}
	@Override
	public String toString() {
		return "Schedule [id=" + id + ", date=" + date + ", time=" + time
				+ ", price=" + price + ", movie_id=" + movie_id
				+ ", screen_id=" + screen_id + "]";
	}
}

class Reservation {
	public String getReservationId() {
		return reservationId;
	}
	public int getSeatCount() {
		return seatCount;
	}
	public int[] getRow() {
		return row;
	}
	public int[] getCol() {
		return col;
	}
	public int getSchedule_id() {
		return schedule_id;
	}
	protected String reservationId;
	protected int seatCount;
	protected int [] row;
	protected int [] col;
	protected int schedule_id;
	public Reservation(String reservationId, int seatCount, int schedule_id) {
		super();
		this.reservationId = reservationId;
		this.seatCount = seatCount;
		this.schedule_id = schedule_id;
		if(seatCount != 0) {
			row = new int[seatCount];
			col = new int[seatCount];
		}
	}
	public void addSeat(int idx, int _row, int _col) {
		if(idx < 0 || idx >= seatCount) {
			return;
		}
		row[idx] = _row;
		col[idx] = _col;
	}
	@Override
	public String toString() {
		return "Reservation [reservationId=" + reservationId + ", seatCount="
				+ seatCount + ", row=" + Arrays.toString(row) + ", col="
				+ Arrays.toString(col) + ", schedule_id=" + schedule_id + "]";
	}
}

class Screen {
	public int getId() {
		return id;
	}
	public int getTheater_id() {
		return theater_id;
	}
	public int getNumber() {
		return number;
	}
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
	public Screen(int id, int theater_id, int number, int row, int col) {
		super();
		this.id = id;
		this.theater_id = theater_id;
		this.number = number;
		this.row = row;
		this.col = col;
	}
	protected int id;
	protected int theater_id;
	protected int number;
	protected int row;
	protected int col;
}

class Ticket {
	
}

public class MoviePageData extends PageData {
	protected Connection conn;
	protected Statement st;
	protected ResultSet rs;
	protected PreparedStatement pstmt;
	
	protected String db_url;
	protected String db_user;
	protected String db_pswd;
	
	protected Date curDate;
	protected DateFormat dateFormat;
	
	protected int curTheaterId;
	protected int curMovieId;
	protected int curScheduleId;
	protected Vector<Integer> curSeatList;
	protected String curReservationId;

	protected int tempMovieId;
	protected String tempMovieInfoField;
	protected String tempReservationId;
	protected String tempReservationInfoField;
	
	MoviePageData() {
		String url = "jdbc:mysql://220.66.67.250:3306/d201019010";
		String user = "201019010";
		String pw = "mis5312";
		curSeatList = new Vector<Integer>();
		
		initDB(url,user,pw);
		initBoxOfficeBasicData();
		resetAllField();
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	}
	public void reset() {
		
	}
	protected void initDB(String url, String user, String pw) {
		db_url = url;
		db_user = user;
		db_pswd = pw;
	}
	protected void initBoxOfficeBasicData(Date date, int id) {
		setCurDate(date);
		setCurTheaterId(id);
	}
	protected void initBoxOfficeBasicData() {
		initBoxOfficeBasicData(new Date(), 1);
	}
	protected Connection ConnectToDB() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(db_url, db_user , db_pswd);
	}
	
	public Vector<Movie> getMovieList(Date _curdate) {
		conn = null;
		st = null;
		rs = null;
		
		Vector<Movie> movieList = new Vector<Movie>();
		
		try {
			conn = ConnectToDB();
			st = conn.createStatement();
			rs = st.executeQuery("select * from Movie where movie_release_date <= '" + dateFormat.format(_curdate) +"'");
			//rs = st.executeQuery("select * from Movie where movie_release_date <= '2015-06-16' ");

			while(rs.next()) {
				Movie movie = new Movie(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
										rs.getString(5), rs.getString(6), rs.getInt(7));
				System.out.println(movie.toString());
				movieList.add(movie);
			}
			rs.close();
			st.close();
			conn.close();
		} catch(Exception e) {
			System.err.println(e.toString());
		}
		return movieList;
	}
	public Vector<Schedule> getScheduleList(int movieId) {
		conn = null;
		st = null;
		rs = null;
		
		Vector<Schedule> scheduleList = new Vector<Schedule>();
	
		try {
			conn = ConnectToDB();
			st = conn.createStatement();
			rs = st.executeQuery("select * from ScreenSchedule where schedule_movie_id = " + movieId);

			while(rs.next()) {
				Schedule schedule = new Schedule(rs.getInt(1), rs.getDate(2), rs.getTime(3), rs.getInt(4), 
										rs.getInt(5), rs.getInt(6));
				System.out.println(schedule.toString());
				scheduleList.add(schedule);
			}
			rs.close();
			st.close();
			conn.close();
		} catch(Exception e) {
			System.err.println(e.toString());
		}
		return scheduleList;
	}
	public ScheduleReservation getScheduleReservationInfo(int scheduleId) {
		conn = null;
		st = null;
		rs = null;
		
		ScheduleReservation info = null;
	
		try {
			conn = ConnectToDB();
			st = conn.createStatement();
			rs = st.executeQuery("select * from Reservation where reservation_schedule_id = " + scheduleId);
			if(rs.next()) {
				rs.last();
				int count = rs.getRow();
				rs.first();
				info = new ScheduleReservation(scheduleId, count );
				info.addSeat(0, rs.getInt(3), rs.getInt(4));
				
				int i=1;
				while(rs.next()) {
					info.addSeat(i, rs.getInt(3), rs.getInt(4));
					i++;
				}
			} else {
				info = new ScheduleReservation(scheduleId,0);
			}
			rs.close();
			st.close();
			conn.close();
		} catch(Exception e) {
			System.err.println(e.toString());
		}
		return info;
	}
	public Reservation getReservation(String reservation_id) {
		conn = null;
		st = null;
		rs = null;
		
		Reservation reserve = null;
	
		try {
			conn = ConnectToDB();
			st = conn.createStatement();
			rs = st.executeQuery("select * from Reservation where reservation_id = '" + reservation_id +"'");
			
			if(rs.next()) {
				rs.last();
				int count = rs.getRow();
				rs.first();
				reserve = new Reservation(rs.getString(1), count, rs.getInt(5));
				reserve.addSeat(0, rs.getInt(3), rs.getInt(4));
				int i=1;
				while(rs.next()) {
					reserve.addSeat(i, rs.getInt(3), rs.getInt(4));
					i++;
				}
			}
			rs.close();
			st.close();
			conn.close();
		} catch(Exception e) {
			System.err.println(e.toString());
		}
		return reserve;
	}
	public Screen getScreen(int screenId) {
		conn = null;
		st = null;
		rs = null;
		
		Screen s = null;
	
		try {
			conn = ConnectToDB();
			st = conn.createStatement();
			rs = st.executeQuery("select * from Screen where screen_id = " + screenId);
			
			if(rs.next()) {
				s = new Screen(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
			}
			rs.close();
			st.close();
			conn.close();
		} catch(Exception e) {
			System.err.println(e.toString());
		}
		return s;
	}
	public Vector<Movie> getTicket(String ticketId) {
		return null;
	}
	public int getTotalSeatCount(int screenId) {
		int count = 0;
		conn = null;
		st = null;
		rs = null;
		
		try {
			conn = ConnectToDB();
			st = conn.createStatement();
			rs = st.executeQuery("select * from Screen where screen_id = " + screenId);
			
			if(rs.next()) {
				count = 1;
				count *= rs.getInt("screen_seatcount_row");
				count *= rs.getInt("screen_seatcount_row");
			}
			rs.close();
			st.close();
			conn.close();
		} catch(Exception e) {
			System.err.println(e.toString());
		}
		return count;
	}
	public int getReservedSeatCount(int scheduleId) {
		int count = 0;
		conn = null;
		st = null;
		rs = null;
		
		try {
			conn = ConnectToDB();
			st = conn.createStatement();
			rs = st.executeQuery("select * from ScreenSchedule where schedule_screen_id = " + scheduleId);
			
			if(rs.next()) {
				rs.last();
				count = rs.getRow();
			}
			
			rs.close();
			st.close();
			conn.close();
		} catch(Exception e) {
			System.err.println(e.toString());
		}
		return count;
	}
	public String getMovieName(int movieId) {
		String name = "";
		conn = null;
		st = null;
		rs = null;
		
		try {
			conn = ConnectToDB();
			st = conn.createStatement();
			rs = st.executeQuery("select * from Movie where movie_id = " + movieId);
			
			if(rs.next()) {
				name = rs.getString("movie_name");
			}
			
			rs.close();
			st.close();
			conn.close();
		} catch(Exception e) {
			System.err.println(e.toString());
		}
		return name;
	}
	public Movie getMovie(int movieId) {
		Movie m = null;
		conn = null;
		st = null;
		rs = null;
		
		try {
			conn = ConnectToDB();
			st = conn.createStatement();
			rs = st.executeQuery("select * from Movie where movie_id = " + movieId);
			
			if(rs.next()) {
				m = new Movie(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getString(6), rs.getInt(7));
			}
			
			rs.close();
			st.close();
			conn.close();
		} catch(Exception e) {
			System.err.println(e.toString());
		}
		return m;
	}
	public Schedule getSchedule(int scheduleId) {
		Schedule sc = null;
		conn = null;
		st = null;
		rs = null;
		
		try {
			conn = ConnectToDB();
			st = conn.createStatement();
			rs = st.executeQuery("select * from ScreenSchedule where schedule_id = " + scheduleId);
			
			if(rs.next()) {
				sc = new Schedule(rs.getInt(1), rs.getDate(2), rs.getTime(3), rs.getInt(4), 
						rs.getInt(5), rs.getInt(6));
			}
			
			rs.close();
			st.close();
			conn.close();
		} catch(Exception e) {
			System.err.println(e.toString());
		}
		return sc;
	}
	public String CreateReservation() {
		int scheduleId = this.getCurScheduleId();
		Vector<Integer> vec = this.getCurSeatList();
		DateFormat form =  new SimpleDateFormat("yyyyMMdd");
		String reserverId = form.format(new Date());
		reserverId = reserverId.substring(2);
		boolean bCreated = false;

		conn = null;
		rs = null;
		st = null;
		pstmt = null;
		
		try {
			conn = ConnectToDB();
			
			conn.setAutoCommit(false);
			
			st = conn.createStatement();
			rs = st.executeQuery("select * from Reservation where reservation_id like '%" + reserverId + "%' order by reservation_id desc");
			String lastReservId = "";
			int lastReservCount;
			if(rs.next()) {
				lastReservId = rs.getString(1);
				lastReservCount = Integer.parseInt(lastReservId.substring(6));
				lastReservCount++;
			} else {
				lastReservCount = 1;
			}
			
			reserverId += String.format("%04d", lastReservCount);
			String sql = "insert into Reservation(reservation_id,reservation_count,reservation_seat_row,reservation_seat_col,reservation_schedule_id,reservation_time) " 
						+ "values(?,?,?,?,?,default)";
			pstmt = conn.prepareStatement(sql);
			for(int i=0; i<vec.size(); i += 2) {
				pstmt.setString(1, reserverId);
				pstmt.setInt(2, i/2 + 1);
				pstmt.setInt(3, vec.get(i));
				pstmt.setInt(4, vec.get(i+1));
				pstmt.setInt(5, scheduleId);
				pstmt.executeUpdate();
			}
			
			conn.commit();
			bCreated = true;
		} catch(Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.err.println(e.toString());
		} finally {
			try {
				rs.close();
				st.close();
				conn.close();
			} catch(Exception e) {
				System.err.println(e.toString());
			}
		}
		if(bCreated) {
			return reserverId;
		} else {
			return null;
		}
	}
	protected void resetAllField() {
		curTheaterId = 0;
		curMovieId = 0;
		curScheduleId = 0;
		curSeatList.removeAllElements();;
		curReservationId = "";
		
		//temp Fields
		tempMovieId = 0;
		tempMovieInfoField = "";
		tempReservationId = "";
		tempReservationInfoField = "";
	}
	public Date getCurDate() {
		return curDate;
	}
	public int getCurTheaterId() {
		return curTheaterId;
	}
	public int getCurMovieId() {
		return curMovieId;
	}
	public int getCurScheduleId() {
		return curScheduleId;
	}
	public Vector<Integer> getCurSeatList() {
		return curSeatList;
	}
	public String getCurReservationId() {
		return curReservationId;
	}
	public void setCurDate(Date curDate) {
		this.curDate = curDate;
	}
	public void setCurTheaterId(int curTheaterId) {
		this.curTheaterId = curTheaterId;
	}
	public void setCurMovieId(int curMovieId) {
		this.curMovieId = curMovieId;
	}
	public void setCurScheduleId(int curScheduleId) {
		this.curScheduleId = curScheduleId;
	}
	public void setCurSeatList(Vector<Integer> curSeatList) {
		this.curSeatList = curSeatList;
	}
	public void setCurReservationId(String curReservationId) {
		this.curReservationId = curReservationId;
	}
}