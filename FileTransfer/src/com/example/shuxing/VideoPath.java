package com.example.shuxing;

public class VideoPath {

	public long ID;
	public String Path;
	
	public VideoPath()
	{
		super();
	}

	public VideoPath(String Path,long iD)
	{
		this.Path=Path;
		this.ID=iD;
	}
	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getPath() {
		return Path;
	}

	public void setPath(String path) {
		Path = path;
	}
}
