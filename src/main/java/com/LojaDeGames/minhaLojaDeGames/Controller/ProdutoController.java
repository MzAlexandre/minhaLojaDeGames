package com.LojaDeGames.minhaLojaDeGames.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LojaDeGames.minhaLojaDeGames.model.Produto;
import com.LojaDeGames.minhaLojaDeGames.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
@CrossOrigin("*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repository;
			
	@GetMapping
	public ResponseEntity<List<Produto>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> GetById(@PathVariable long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("descricao/{descricao}")
	public ResponseEntity<List<Produto>> GetByDescricao(@PathVariable String descricao){
		return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao));
	}
	
	@PostMapping
    public ResponseEntity<Produto> post (@RequestBody Produto produto){
    	return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
    }
    
     @PutMapping
    public ResponseEntity<Produto> put (@RequestBody Produto produto){
    	return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
    }
    
    @DeleteMapping("/{id}")
	public void delete(@PathVariable long id){
		repository.deleteById(id);
	}
    
    
}
