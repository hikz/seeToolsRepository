package com.seetools.framework;



public class HIXTemplateResourcePool extends ResourcePool<HIXTemplateResource> {
	
		public HIXTemplateResourcePool(int size)
		{
			this(size,false);
		}
		
		public HIXTemplateResourcePool(int size, Boolean dynamicCreation)
		{
			super(size,dynamicCreation);
			createPool();
			
		}
	
	    @Override
	    protected HIXTemplateResource createObject() {
	        return new HIXTemplateResource(createdObjects+1);
	    }
}
