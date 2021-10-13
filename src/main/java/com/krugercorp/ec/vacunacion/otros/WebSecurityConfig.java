package com.krugercorp.ec.vacunacion.otros;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * configuracion para filtro y verificacion
     * @param http seguridad http
     * @throws Exception probable excepcion
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .addFilterAfter(new AuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,
                        "/empleado/login",
                        "/resetPass").permitAll()
                .antMatchers("/v2/api-docs",
                        "/api-docs/swagger-config**",
                        "/api-docs**",
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/webjars/**").permitAll().
                antMatchers("/empleado/registro/",
                        "/empleado/consulta/cedula=**",
                        "/empleado/eliminar/cedula=**",
                        "/reporte/vacunados/",
                        "/reporte/no_vacunados/",
                        "/reporte/tipovacuna/idTipoVacuna={\\d+}",
                        "/reporte/entre_fechas/desde=**/hasta=**",
                        "/param/lista_roles/",
                        "/param/lista_tipo_vacunas/").access("hasAnyAuthority('Administrador')").
                antMatchers("/empleado/actualizar/",
                        "/empleado/consulta/cedula=**",
                        "/param/lista_roles/**",
                        "/param/lista_tipo_vacunas/**").access("hasAnyAuthority('Empleado')")
                .anyRequest().authenticated();
    }

}