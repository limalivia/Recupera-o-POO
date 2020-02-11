package edu.br.ifal.lista;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListaContatosControlador {
    @Autowired
    Repositorio repositorio;

    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("adicionar.html");
    }

    @RequestMapping("/listar")
    public ModelAndView listar() {
        ModelAndView model = new ModelAndView("listar");
        model.addObject("contatos", repositorio.findAll());

        return model;
    }

    @RequestMapping("/salvar")
    public ModelAndView Salvar(Contato contato) {
        repositorio.save(contato);

        return new ModelAndView("redirect:/listar");
    }

    @RequestMapping("/Excluir/{id}")
    public ModelAndView Excluir(@PathVariable(name = "id") Long id) {
        repositorio.deleteById(id);

        return new ModelAndView("redirect:/listar");
    }

    @RequestMapping("/atualizar/{id}")
    public ModelAndView atualizar(@PathVariable(name = "id") Long id) {
        ModelAndView model = new ModelAndView("adicionar");
        Optional<Contato> opcao = repositorio.findById(id);
       model.addObject("contato",opcao.get());
        return model;
    }
}