package org.unibl.etf.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.unibl.etf.encoder.MyPasswordEncoder;

@Configuration
@EnableWebSecurity
public class ZaposleniSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource dataSource;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().passwordEncoder(new MyPasswordEncoder()).dataSource(dataSource)
				.usersByUsernameQuery("select korisnicko_ime, lozinka, enabled from zaposleni where korisnicko_ime=?")
				.authoritiesByUsernameQuery("select korisnicko_ime, role from zaposleni where korisnicko_ime=?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();
		http.httpBasic().and().authorizeRequests().antMatchers("/api/**").authenticated();
	}
}
