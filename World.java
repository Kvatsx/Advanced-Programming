// Code By:- Kaustav Vats (2016048)
import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

abstract class Animal {
	private int x, y, Timestamp, Number;
	private double Health;
	protected int TurnCounter = 0;
	public Animal(int X, int Y, int T, int H, int N)
	{
		this.x = X;
		this.y = Y;
		this.Timestamp = T;
		this.Health = H;
		this.Number = N;
	}
	protected void setNumber(int xyz)
	{
		this.Number = xyz;
	}
	protected int getNumber()
	{
		return this.Number;
	}
	protected int getx()
	{
		return x;
	}
	protected int gety()
	{
		return y;
	}
	protected void setx(int xyz)
	{
		x = xyz;
	}
	protected void sety(int xyz)
	{
		y = xyz;
	}
	protected void setTimestamp(int z)
	{
		Timestamp = z;
	}
	protected int getTimestamp()
	{
		return Timestamp;
	}
	protected double getHealth()
	{
		return Health;
	}
	protected void setHealth(double xyz)
	{
		Health = xyz;
	}
	protected int getDistance()
	{
		return x*x+y*y;
	}
	protected abstract Object[] TakeTurn(Grassland gLand1, Grassland gLand2, Herbivore first_H, Herbivore second_H, Carnivore first_C, Carnivore second_C);
}

class Herbivore extends Animal {
	private int Gcapacity;
	public Herbivore(int X, int Y, int T, int H, int G, int n)
	{
		super(X,Y,T,H,n);
		this.setGcapacity(G);
	}
	@Override
	public Object[] TakeTurn(Grassland gLand1, Grassland gLand2, Herbivore first_H, Herbivore second_H, Carnivore first_C, Carnivore second_C)
	{
		TurnCounter++;
		int d1 = (gLand1.getx()-this.getx())*(gLand1.getx()-this.getx()) + (gLand1.gety()-this.gety())*(gLand1.gety()-this.gety());
		int d2 = (gLand2.getx()-this.getx())*(gLand2.getx()-this.getx()) + (gLand2.gety()-this.gety())*(gLand2.gety()-this.gety());
		if ( first_C == null && second_C == null )
		{
			if ( Math.random() < 0.5 )
			{
				if ( d1 <= d2 )
				{
					// Herbivose will go towards Grass gLand1.			
					if ( Math.sqrt(d1) <= gLand1.getRadius() )
					{
						double m = Math.sqrt(d2)-5;
						int n = 5;
						double xn = ((double)(m*(this.getx())+n*(gLand2.getx())))/(double)(m+n);
						double yn = ((double)(m*(this.getx())+n*(gLand2.gety())))/(double)(m+n);
						this.setx((int)Math.round(xn));
						this.sety((int)Math.round(yn));
					}
					else
					{
						double m = Math.sqrt(d1)-5;
						int n = 5;
						double xn = ((double)(m*(this.getx())+n*(gLand1.getx())))/(double)(m+n);
						double yn = ((double)(m*(this.getx())+n*(gLand1.gety())))/(double)(m+n);
						this.setx((int)Math.round(xn));
						this.sety((int)Math.round(yn));
					}
				}
				else
				{
					// Herbivore will go towards Grass gLand2.
					if ( Math.sqrt(d2) < gLand2.getRadius() )
					{
						double m = Math.sqrt(d1)-5;
						int n = 5;
						double xn = ((double)(m*(this.getx())+n*(gLand1.getx())))/(double)(m+n);
						double yn = ((double)(m*(this.getx())+n*(gLand1.gety())))/(double)(m+n);
						this.setx((int)Math.round(xn));
						this.sety((int)Math.round(yn));
					}
					else
					{
						double m = Math.sqrt(d2)-5;
						int n = 5;
						double xn = ((double)(m*(this.getx())+n*(gLand2.getx())))/(double)(m+n);
						double yn = ((double)(m*(this.getx())+n*(gLand2.gety())))/(double)(m+n);
						this.setx((int)Math.round(xn));
						this.sety((int)Math.round(yn));
					}
				}
			}
			else
			{
				// Stay at his position.
			}
		}
		else
		{
			if ( Math.sqrt(d1) > gLand1.getRadius() && Math.sqrt(d2) > gLand2.getRadius() )
			{
				int x = (int )(Math.random() *100  + 1);
				if ( x <= 5 )
				{
					// Herbivore will stay
				}
				else
				{
					x = (int )(Math.random() *100  + 1);
					if ( x <= 65 ) 
					{
						if ( d1 <= d2 )
						{
							double m = Math.sqrt(d1)-5;
							int n = 5;
							double xn = ((double)(m*(this.getx())+n*(gLand1.getx())))/(double)(m+n);
							double yn = ((double)(m*(this.getx())+n*(gLand1.gety())))/(double)(m+n);
							this.setx((int)Math.round(xn));
							this.sety((int)Math.round(yn));
						}
						else
						{
							double m = Math.sqrt(d2)-5;
							int n = 5;
							double xn = ((double)(m*(this.getx())+n*(gLand2.getx())))/(double)(m+n);
							double yn = ((double)(m*(this.getx())+n*(gLand2.gety())))/(double)(m+n);
							this.setx((int)Math.round(xn));
							this.sety((int)Math.round(yn));
						}
					}
					else
					{
						d1 = Integer.MAX_VALUE;
						d2 = Integer.MAX_VALUE;
						if ( first_C != null )
						{
							d1 = (first_C.getx()-this.getx())*(first_C.getx()-this.getx()) + (first_C.gety()-this.gety())*(first_C.gety()-this.gety());
						}
						if ( second_C != null )
						{
							d2 = (second_C.getx()-this.getx())*(second_C.getx()-this.getx()) + (second_C.gety()-this.gety())*(second_C.gety()-this.gety());
						}
						
						if ( d1 <= d2 )
						{
							double m = Math.sqrt(d1)+4;
							int n = 4;
							double xn = ((double)(m*(this.getx())-n*(first_C.getx())))/(double)(m-n);
							double yn = ((double)(m*(this.getx())-n*(first_C.gety())))/(double)(m-n);
							this.setx((int)Math.round(xn));
							this.sety((int)Math.round(yn));
						}
						else
						{
							double m = Math.sqrt(d2)+4;
							int n = 4;
							double xn = ((double)(m*(this.getx())-n*(second_C.getx())))/(double)(m-n);
							double yn = ((double)(m*(this.getx())-n*(second_C.gety())))/(double)(m-n);
							this.setx((int)Math.round(xn));
							this.sety((int)Math.round(yn));
						}
					}
				}
			}
			else
			{
				this.TurnCounter = 0;
				if ( Math.sqrt(d1) <=  gLand1.getRadius() )
				{
					if ( gLand1.getGrass() >= this.Gcapacity )
					{
						if ( Math.random() <= 0.9 )
						{
							gLand1.setGrass(gLand1.getGrass()-this.Gcapacity);
							this.setHealth(this.getHealth()*0.5+this.getHealth());
						}
						else
						{
//							System.out.println("Maar gaya vo");
							this.setHealth(this.getHealth()-25);
							int x = (int )(Math.random() *100  + 1);
							d1 = Integer.MAX_VALUE;
							if ( first_C != null )
							{
								d1 = (first_C.getx()-this.getx())*(first_C.getx()-this.getx()) + (first_C.gety()-this.gety())*(first_C.gety()-this.gety());
							}
							d2 = Integer.MAX_VALUE;
							if ( second_C != null )
							{
								d2 = (second_C.getx()-this.getx())*(second_C.getx()-this.getx()) + (second_C.gety()-this.gety())*(second_C.gety()-this.gety());
							}
							if ( x <= 50 )
							{
								if ( d1 <= d2 )
								{
									double m = Math.sqrt(d1)+2;
									int n = 2;
									double xn = ((double)(m*(this.getx())-n*(first_C.getx())))/(double)(m-n);
									double yn = ((double)(m*(this.getx())-n*(first_C.gety())))/(double)(m-n);
									this.setx((int)Math.round(xn));
									this.sety((int)Math.round(yn));
								}
								else
								{
									double m = Math.sqrt(d2)+2;
									int n = 2;
									double xn = ((double)(m*(this.getx())-n*(second_C.getx())))/(double)(m-n);
									double yn = ((double)(m*(this.getx())-n*(second_C.gety())))/(double)(m-n);
									this.setx((int)Math.round(xn));
									this.sety((int)Math.round(yn));
								}
							}
							else
							{
								d1 = (gLand1.getx()-this.getx())*(gLand1.getx()-this.getx()) + (gLand1.gety()-this.gety())*(gLand1.gety()-this.gety());
								d2 = (gLand2.getx()-this.getx())*(gLand2.getx()-this.getx()) + (gLand2.gety()-this.gety())*(gLand2.gety()-this.gety());
								if ( d1 <= d2 )
								{
									double m = Math.sqrt(d1)-3;
									int n = 3;
									double xn = ((double)(m*(this.getx())+n*(first_H.getx())))/(double)(m+n);
									double yn = ((double)(m*(this.getx())+n*(first_H.gety())))/(double)(m+n);
									this.setx((int)Math.round(xn));
									this.sety((int)Math.round(yn));
								}
								else
								{
									double m = Math.sqrt(d2)-3;
									int n = 3;
									double xn = ((double)(m*(this.getx())+n*(second_H.getx())))/(double)(m+n);
									double yn = ((double)(m*(this.getx())+n*(second_H.gety())))/(double)(m+n);
									this.setx((int)Math.round(xn));
									this.sety((int)Math.round(yn));
								}
							}
						}
					}
					else
					{
						int x = (int )(Math.random() *100  + 1);
						if ( x <= 20 )
						{
							this.setHealth(this.getHealth()*0.2+this.getHealth());
							gLand1.setGrass(0);
						}
						else
						{
//							System.out.println("maar gaya vo");
							this.setHealth(this.getHealth()-25);
							x = (int )(Math.random() *100  + 1);
							d1 = Integer.MAX_VALUE;
							if ( first_C != null )
							{
								d1 = (first_C.getx()-this.getx())*(first_C.getx()-this.getx()) + (first_C.gety()-this.gety())*(first_C.gety()-this.gety());
							}
							d2 = Integer.MAX_VALUE;
							if ( second_C != null )
							{
								d2 = (second_C.getx()-this.getx())*(second_C.getx()-this.getx()) + (second_C.gety()-this.gety())*(second_C.gety()-this.gety());
							}
							if ( x <= 70 )
							{
								if ( d1 < d2 )
								{
									double m = Math.sqrt(d1)+4;
									int n = 4;
									double xn = ((double)(m*(this.getx())-n*(first_C.getx())))/(double)(m-n);
									double yn = ((double)(m*(this.getx())-n*(first_C.gety())))/(double)(m-n);
									this.setx((int)Math.round(xn));
									this.sety((int)Math.round(yn));
								}
								else
								{
									double m = Math.sqrt(d2)+4;
									int n = 4;
									double xn = ((double)(m*(this.getx())-n*(second_C.getx())))/(double)(m-n);
									double yn = ((double)(m*(this.getx())-n*(second_C.gety())))/(double)(m-n);
									this.setx((int)Math.round(xn));
									this.sety((int)Math.round(yn));
								}
							}
							else
							{
								d1 = (gLand1.getx()-this.getx())*(gLand1.getx()-this.getx()) + (gLand1.gety()-this.gety())*(gLand1.gety()-this.gety());
								d2 = (gLand2.getx()-this.getx())*(gLand2.getx()-this.getx()) + (gLand2.gety()-this.gety())*(gLand2.gety()-this.gety());
								if ( d1 <= d2 )
								{
									double m = Math.sqrt(d1)-2;
									int n = 2;
									double xn = ((double)(m*(this.getx())+n*(first_H.getx())))/(double)(m+n);
									double yn = ((double)(m*(this.getx())+n*(first_H.gety())))/(double)(m+n);
									this.setx((int)Math.round(xn));
									this.sety((int)Math.round(yn));
								}
								else
								{
									double m = Math.sqrt(d2)-2;
									int n = 2;
									double xn = ((double)(m*(this.getx())+n*(second_H.getx())))/(double)(m+n);
									double yn = ((double)(m*(this.getx())+n*(second_H.gety())))/(double)(m+n);
									this.setx((int)Math.round(xn));
									this.sety((int)Math.round(yn));
								}
							}
						}
					}
				}
				else
				{
					if ( gLand2.getGrass() >= this.Gcapacity )
					{
						int x = (int )(Math.random() *100  + 1);
						if ( x <= 90 )
						{
							gLand2.setGrass(gLand2.getGrass()-this.Gcapacity);
							this.setHealth(this.getHealth()*0.5+this.getHealth());
						}
						else
						{
							this.setHealth(this.getHealth()-25);
							x = (int )(Math.random() *100  + 1);
							d1 = Integer.MAX_VALUE;
							if ( first_C != null )
							{
								d1 = (first_C.getx()-this.getx())*(first_C.getx()-this.getx()) + (first_C.gety()-this.gety())*(first_C.gety()-this.gety());
							}
							d2 = Integer.MAX_VALUE;
							if ( second_C != null )
							{
								d2 = (second_C.getx()-this.getx())*(second_C.getx()-this.getx()) + (second_C.gety()-this.gety())*(second_C.gety()-this.gety());
							}
							if ( x <= 50 )
							{
								if ( d1 < d2 )
								{
									double m = Math.sqrt(d1)+2;
									int n = 2;
									double xn = ((double)(m*(this.getx())-n*(first_C.getx())))/(double)(m-n);
									double yn = ((double)(m*(this.getx())-n*(first_C.gety())))/(double)(m-n);
									this.setx((int)Math.round(xn));
									this.sety((int)Math.round(yn));
								}
								else
								{
									double m = Math.sqrt(d2)+2;
									int n = 2;
									double xn = ((double)(m*(this.getx())-n*(second_C.getx())))/(double)(m-n);
									double yn = ((double)(m*(this.getx())-n*(second_C.gety())))/(double)(m-n);
									this.setx((int)Math.round(xn));
									this.sety((int)Math.round(yn));
								}
							}
							else
							{
								d1 = (gLand1.getx()-this.getx())*(gLand1.getx()-this.getx()) + (gLand1.gety()-this.gety())*(gLand1.gety()-this.gety());
								d2 = (gLand2.getx()-this.getx())*(gLand2.getx()-this.getx()) + (gLand2.gety()-this.gety())*(gLand2.gety()-this.gety());
								if ( d1 <= d2 )
								{
									double m = Math.sqrt(d1)-3;
									int n = 3;
									double xn = ((double)(m*(this.getx())+n*(first_H.getx())))/(double)(m+n);
									double yn = ((double)(m*(this.getx())+n*(first_H.gety())))/(double)(m+n);
									this.setx((int)Math.round(xn));
									this.sety((int)Math.round(yn));
								}
								else
								{
									double m = Math.sqrt(d2)-3;
									int n = 3;
									double xn = ((double)(m*(this.getx())+n*(second_H.getx())))/(double)(m+n);
									double yn = ((double)(m*(this.getx())+n*(second_H.gety())))/(double)(m+n);
									this.setx((int)Math.round(xn));
									this.sety((int)Math.round(yn));
								}
							}
						}
					}
					else
					{
						int x = (int )(Math.random() *100  + 1);
						if ( x <= 20 )
						{
							gLand2.setGrass(0);
							this.setHealth(this.getHealth()*0.2+this.getHealth());
						}
						else
						{
							this.setHealth(this.getHealth()-25);
							x = (int )(Math.random() *100  + 1);
							d1 = Integer.MAX_VALUE;
							if ( first_C != null )
							{
								d1 = (first_C.getx()-this.getx())*(first_C.getx()-this.getx()) + (first_C.gety()-this.gety())*(first_C.gety()-this.gety());
							}
							d2 = Integer.MAX_VALUE;
							if ( second_C != null )
							{
								d2 = (second_C.getx()-this.getx())*(second_C.getx()-this.getx()) + (second_C.gety()-this.gety())*(second_C.gety()-this.gety());
							}
							if ( x <= 70 )
							{
								if ( d1 < d2 )
								{
									double m = Math.sqrt(d1)+4;
									int n = 4;
									double xn = ((double)(m*(this.getx())-n*(first_C.getx())))/(double)(m-n);
									double yn = ((double)(m*(this.getx())-n*(first_C.gety())))/(double)(m-n);
									this.setx((int)Math.round(xn));
									this.sety((int)Math.round(yn));
								}
								else
								{
									double m = Math.sqrt(d2)+4;
									int n = 4;
									double xn = ((double)(m*(this.getx())-n*(second_C.getx())))/(double)(m-n);
									double yn = ((double)(m*(this.getx())-n*(second_C.gety())))/(double)(m-n);
									this.setx((int)Math.round(xn));
									this.sety((int)Math.round(yn));
								}
							}
							else
							{
								d1 = (gLand1.getx()-this.getx())*(gLand1.getx()-this.getx()) + (gLand1.gety()-this.gety())*(gLand1.gety()-this.gety());
								d2 = (gLand2.getx()-this.getx())*(gLand2.getx()-this.getx()) + (gLand2.gety()-this.gety())*(gLand2.gety()-this.gety());
								if ( d1 <= d2 )
								{
									double m = Math.sqrt(d1)-2;
									int n = 2;
									double xn = ((double)(m*(this.getx())+n*(first_H.getx())))/(double)(m+n);
									double yn = ((double)(m*(this.getx())+n*(first_H.gety())))/(double)(m+n);
									this.setx((int)Math.round(xn));
									this.sety((int)Math.round(yn));
								}
								else
								{
									double m = Math.sqrt(d2)-2;
									int n = 2;
									double xn = ((double)(m*(this.getx())+n*(second_H.getx())))/(double)(m+n);
									double yn = ((double)(m*(this.getx())+n*(second_H.gety())))/(double)(m+n);
									this.setx((int)Math.round(xn));
									this.sety((int)Math.round(yn));
								}
							}
						}
					}
				}
			}
		}
		if ( this.TurnCounter >= 7 )
		{
			this.setHealth(this.getHealth() - 5);
		}
		if ( this.getHealth() > 0 )
		{
			System.out.println("It’s health after taking turn is "+this.getHealth());
		}
		else if ( this.getHealth() <= 0 )
		{
			System.out.println("It is dead.");
		}
		Object[] zxc = new Object[6];
		zxc[0] = first_H;
		zxc[1] = second_H;
		zxc[2] = first_C;
		zxc[3] = second_C;
		zxc[4] = gLand1;
		zxc[5] = gLand2;
		return zxc;
	}
	
	public int getGcapacity() {
		return Gcapacity;
	}

	public void setGcapacity(int gcapacity) {
		Gcapacity = gcapacity;
	}
}

class Carnivore extends Animal {
	
	public Carnivore(int X, int Y, int T, int H,int n)
	{
		super(X,Y,T,H,n);
	}
	@Override
	public Object[] TakeTurn(Grassland gLand1, Grassland gLand2, Herbivore first_H, Herbivore second_H, Carnivore first_C, Carnivore second_C)
	{
		TurnCounter++;
		if ( first_H != null || second_H != null )
		{
			int d1 = Integer.MAX_VALUE;
			int d2 = Integer.MAX_VALUE;
			if ( first_H != null )
			{
				d1 = (first_H.getx()-this.getx())*(first_H.getx()-this.getx()) + (first_H.gety()-this.gety())*(first_H.gety()-this.gety());
			}
			if ( second_H != null )
			{
				d2 = (second_H.getx()-this.getx())*(second_H.getx()-this.getx()) + (second_H.gety()-this.gety())*(second_H.gety()-this.gety());
			}
			if ( d1 == 1 || d2 == 1 )
			{
				if ( d1 == 1 )
				{
					this.setHealth(this.getHealth()+(first_H.getHealth()*(2/3)));
					first_H.setHealth(0);
				}
				else if ( d2 == 1 )
				{
					this.setHealth(this.getHealth()+(second_H.getHealth()*(2/3)));
					second_H.setHealth(0);
				}
				this.TurnCounter = 0;
			}
			
			else if ( d1 != 1 && d2 != 1 )
			{
				d1 = (gLand1.getx()-this.getx())*(gLand1.getx()-this.getx()) + (gLand1.gety()-this.gety())*(gLand1.gety()-this.gety());
				d2 = (gLand2.getx()-this.getx())*(gLand2.getx()-this.getx()) + (gLand2.gety()-this.gety())*(gLand2.gety()-this.gety());
				if ( Math.sqrt(d1) > gLand1.getRadius() && Math.sqrt(d2) > gLand2.getRadius() )
				{
					if ( Math.random() < 0.92 )
					{
						int d3 = Integer.MAX_VALUE;
						int d4 = Integer.MAX_VALUE;
						if ( first_H != null )
						{
							d3 = (first_H.getx()-this.getx())*(first_H.getx()-this.getx()) + (first_H.gety()-this.gety())*(first_H.gety()-this.gety());
						}
						if ( second_H != null )
						{
							d4 = (second_H.getx()-this.getx())*(second_H.getx()-this.getx()) + (second_H.gety()-this.gety())*(second_H.gety()-this.gety());
						}
						if ( d3 < d4 )
						{
							double m = Math.sqrt(d3)-4;
							int n = 4;
							double xn = ((double)(m*(this.getx())+n*(first_H.getx())))/(double)(m+n);
							double yn = ((double)(m*(this.getx())+n*(first_H.gety())))/(double)(m+n);
							this.setx((int)Math.round(xn));
							this.sety((int)Math.round(yn));
						}
						else
						{
							// if d4 < d3
							double m = Math.sqrt(d4)-4;
							int n = 4;
							double xn = ((double)(m*(this.getx())+n*(second_H.getx())))/(double)(m+n);
							double yn = ((double)(m*(this.getx())+n*(second_H.gety())))/(double)(m+n);
							this.setx((int)Math.round(xn));
							this.sety((int)Math.round(yn));
						}
					}
					else
					{
						this.setHealth(this.getHealth() - 60);
					}
				}
				else
				{
					if ( Math.random() <= 0.25 )
					{
						this.setHealth(this.getHealth() - 30);
					}
					else
					{
						int d3 = Integer.MAX_VALUE;
						int d4 = Integer.MAX_VALUE;
						if ( first_H != null )
						{
							d3 = (first_H.getx()-this.getx())*(first_H.getx()-this.getx()) + (first_H.gety()-this.gety())*(first_H.gety()-this.gety());
						}
						if ( second_H != null )
						{
							d4 = (second_H.getx()-this.getx())*(second_H.getx()-this.getx()) + (second_H.gety()-this.gety())*(second_H.gety()-this.gety());
						}
						if ( d3 <= d4 )
						{
							double m = Math.sqrt(d3)-2;
							int n = 2;
							double xn = ((double)(m*(this.getx())+n*(first_H.getx())))/(double)(m+n);
							double yn = ((double)(m*(this.getx())+n*(first_H.gety())))/(double)(m+n);
							this.setx((int)Math.round(xn));
							this.sety((int)Math.round(yn));
						}
						else
						{
							// if d4 < d3
							double m = Math.sqrt(d4)-2;
							int n = 2;
							double xn = ((double)(m*(this.getx())+n*(second_H.getx())))/(double)(m+n);
							double yn = ((double)(m*(this.getx())+n*(second_H.gety())))/(double)(m+n);
							this.setx((int)Math.round(xn));
							this.sety((int)Math.round(yn));
						}
					}
				}
			}
		}
		int d0 = (first_H.getx()-this.getx())*(first_H.getx()-this.getx()) + (first_H.gety()-this.gety())*(first_H.gety()-this.gety());
		int d5 = (second_H.getx()-this.getx())*(second_H.getx()-this.getx()) + (second_H.gety()-this.gety())*(second_H.gety()-this.gety());
		if ( TurnCounter >= 7 && d0 > 5 && d5 > 5 )
		{
			this.setHealth(this.getHealth() - 6);
		}
		else if ( d0 <= 5 || d5 <= 5 )
		{
			TurnCounter = 0;
		}
		if ( this.getHealth() > 0 )
		{
			System.out.println("It’s health after taking turn is "+this.getHealth());
		}
		else if ( this.getHealth() <= 0 )
		{
			System.out.println("It is dead.");
		}
		Object[] zxc = new Object[6];
		zxc[0] = first_H;
		zxc[1] = second_H;
		zxc[2] = first_C;
		zxc[3] = second_C;
		zxc[4] = gLand1;
		zxc[5] = gLand2;
		return zxc;
	}
}

class Grassland {
	private int x, y, Radius, Grass; 
	
	public Grassland(int X, int Y, int R, int G)
	{
		this.x = X;
		this.y = Y;
		this.Radius = R;
		this.Grass = G;
	}
	public int getx()
	{
		return x;
	}
	public int gety()
	{
		return y;
	}
	public int getRadius()
	{
		return Radius;
	}
	public int getGrass()
	{
		return Grass;
	}
	public void setGrass(int xyz)
	{
		Grass = xyz;
	}
}

public class World {
	private Grassland GLand1, GLand2;
	private Herbivore First_H, Second_H;
	private Carnivore First_C, Second_C;
	private PriorityQueue<Animal> Arr;
	private static int Time, Max;
	
	public World()
	{
		Arr = new PriorityQueue<Animal>(4, new Comparator<Animal>() {
			public int compare(Animal x, Animal y)
			{
				if ( x.getTimestamp() < y.getTimestamp() )
				{
					return -1;
				}
				else if ( x.getTimestamp() == y.getTimestamp() )
				{
					if ( x.getHealth() > y.getHealth() )
					{
						return -1;
					}
					else if ( x.getHealth() == y.getHealth() )
					{
						if ( x.getClass().getName().equals("Herbivore") && y.getClass().getName().equals("Carnivore") )
						{
							return -1;
						}
						else if ( y.getClass().getName().equals("Herbivore") && x.getClass().getName().equals("Carnivore") )
						{
							return 1;
						}
						else 
						{
							if ( x.getDistance() < y.getDistance() )
							{
								return -1;
							}
							else
							{
								return 1;
							}
						}
					}
					else
					{
						return 1;
					}
				}
				else
				{
					return 1;
				}
			}
		});
	}
	
	public void setGrasslands(int x1, int y1, int r1, int g1, int x2, int y2,int r2, int g2)
	{
		GLand1= new Grassland(x1,y1,r1,g1);
		GLand2 = new Grassland(x2,y2,r2,g2);
	}
	public Grassland getGLand1() {
		return GLand1;
	}
	public Grassland getGLand2() {
		return GLand2;
	}
	public void setHerbivores(int x1, int y1, int t1, int h1, int g1, int x2, int y2, int t2, int h2, int g2)
	{
		First_H = new Herbivore(x1,y1,t1,h1,g1,1);
		Second_H = new Herbivore(x2,y2,t2,h2,g2,2);
	}
	public Herbivore getHerbivore1()
	{
		return First_H;
	}
	public Herbivore getHerbivore2()
	{
		return Second_H;
	}
	public void setCarnivores(int x1, int y1, int t1, int h1, int x2, int y2, int t2, int h2)
	{
		First_C = new Carnivore(x1,y1,t1,h1,1);
		Second_C = new Carnivore(x2,y2,t2,h2,2);
	}
	public Carnivore getCarnivore1()
	{
		return First_C;
	}
	public Carnivore getCarnivore2()
	{
		return Second_C;
	}
	public void StartSimulation() 
	{
		Arr.add(First_H);
		Arr.add(Second_H);
		Arr.add(First_C);
		Arr.add(Second_C);
		System.out.println("The Simulation Begins -");
		int Counter = 0;
		while ( Arr.size() > 0 && Counter < Time )
		{
			boolean f1=false,f2=false,f3=false,f4=false;
			boolean Main = false;
			Animal Temp = Arr.poll();
			if ( Temp.getHealth() > 0 )
			{
				if ( Temp.getClass().getName().equals("Herbivore"))
				{
					if ( Temp.getNumber() == 1 )
					{
						f1 = true;
	//					First_H = null;
						System.out.println("It is First Herbivore.");
					}
					else
					{
						f2 = true;
	//					Second_H = null;
						System.out.println("It is Second Herbivore.");
					}
				}
				else if ( Temp.getClass().getName().equals("Carnivore"))
				{
					if ( Temp.getNumber() == 1 )
					{
						f3 = true;
	//					First_C = null;
						System.out.println("It is First Carnivore.");
					}
					else
					{
						f4 = true;
	//					Second_C = null;
						System.out.println("It is Second Carnivore.");
					}
				}
				Object[] zxc = Temp.TakeTurn(GLand1, GLand2, First_H, Second_H, First_C, Second_C);
				First_H = (Herbivore) zxc[0];
				Second_H = (Herbivore) zxc[1];
				First_C = (Carnivore) zxc[2];
				Second_C = (Carnivore) zxc[3];
				GLand1 = (Grassland) zxc[4];
				GLand2 = (Grassland) zxc[5];
				if ( f1 && First_H.getHealth() > 0)
				{
					Main = true;
				}
				else if ( f2 && Second_H.getHealth() > 0)
				{
					Main = true;
				}
				else if ( f3 && First_C.getHealth() > 0)
				{
					Main = true;
				}
				else if ( f4 && Second_C.getHealth() > 0)
				{
					Main = true;
				}
				if ( Main )
				{
					int z = (int )(Math.random() *(Time-1)  + (Max+1));
					if ( z < Time-1 )
					{
						Temp.setTimestamp(z);
						Arr.add(Temp);
						Max = z;
					}
				}
				System.out.println();
				Counter++;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {	
		Reader.init(System.in);
		World myWorld = new World();
		Max = 0;
		System.out.println("Enter Total Final Time for Simulation:");
		Time = Reader.nextInt();
		
		System.out.println("Enter x, y centre, radius and Grass Available for First Grassland:");
		int x1 = Reader.nextInt();
		int y1 = Reader.nextInt();
		int r1 = Reader.nextInt();
		int g1 = Reader.nextInt();
		
		System.out.println("Enter x, y centre, radius and Grass Available for Second Grassland:");
		int x2 = Reader.nextInt();
		int y2 = Reader.nextInt();
		int r2 = Reader.nextInt();
		int g2 = Reader.nextInt();
		myWorld.setGrasslands(x1, y1, r1, g1, x2, y2, r2, g2);
		
		System.out.println("Enter Health and Grass Capacity for Herbivores:");
		int h1 = Reader.nextInt();
		g1 = Reader.nextInt();
		
		System.out.println("Enter x, y position and timestamp for First Herbivore:");
		x1 = Reader.nextInt();
		y1 = Reader.nextInt();
		r1 = Reader.nextInt();
		if ( r1 > Max )
		{
			Max = r1;
		}
		
		System.out.println("Enter x, y position and timestamp for Second Herbivore:");
		x2 = Reader.nextInt();
		y2 = Reader.nextInt();
		r2 = Reader.nextInt();
		myWorld.setHerbivores(x1, y1, r1, h1, g1, x2, y2, r2, h1, g1);
		if ( r1 > Max )
		{
			Max = r1;
		}
		
		System.out.println("Enter Health for Carnivore:");
		h1 = Reader.nextInt();
		
		System.out.println("Enter x, y position and timestamp for First Carnivore:");
		x1 = Reader.nextInt();
		y1 = Reader.nextInt();
		r1 = Reader.nextInt();
		if ( r1 > Max )
		{
			Max = r1;
		}
		
		System.out.println("Enter x, y position and timestamp for Second Carnivore:");
		x2 = Reader.nextInt();
		y2 = Reader.nextInt();
		r2 = Reader.nextInt();
		if ( r1 > Max )
		{
			Max = r1;
		}
		myWorld.setCarnivores(x1, y1, r1, h1, x2, y2, r2, h1);
		
		myWorld.StartSimulation();
	}

}


/** Class for buffered reading int and floatvalues */
class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    /** call this method to initialize reader for InputStream */
    static void init(InputStream input) {
        reader = new BufferedReader(
                     new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }

    /** get next word */
    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                   reader.readLine() );
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt( next() );
    }
	
    static float nextFloat() throws IOException {
        return Float.parseFloat( next() );
    }

    static long nextLong() throws IOException {
        return Long.parseLong( next() );
    }
}