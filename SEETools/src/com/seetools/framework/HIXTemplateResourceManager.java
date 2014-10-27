package com.seetools.framework;

public class HIXTemplateResourceManager {

	private HIXTemplateResourcePool templatePool;
	
	private final int POOL_SIZE = 10;

	private static HIXTemplateResourceManager instance;

	private HIXTemplateResourceManager()
	{
		templatePool = new HIXTemplateResourcePool(POOL_SIZE,true);
	}

	public static HIXTemplateResourceManager getInstance() {
		if(instance == null) {

			synchronized(HIXTemplateResourceManager.class) 
			{
				if(instance == null) {
					instance = new HIXTemplateResourceManager();
				}
			}
		}
		return instance;
	}
	
	
	public HIXTemplateResource getHIXTemplate() throws Exception
	{
		return templatePool.acquire();
	}

}
