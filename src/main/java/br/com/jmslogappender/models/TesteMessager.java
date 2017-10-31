package br.com.jmslogappender.models;

import org.apache.log4j.Logger;

public class TesteMessager {
		private  static Logger logger = Logger.getLogger(TesteMessager.class);
		 
		 public static void main(String[] args) {
			logger.info("Teste info");
			logger.error("Teste error");
		}

}
