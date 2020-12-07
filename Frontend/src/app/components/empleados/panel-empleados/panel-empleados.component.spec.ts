import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PanelEmpleadosComponent } from './panel-empleados.component';

describe('PanelEmpleadosComponent', () => {
  let component: PanelEmpleadosComponent;
  let fixture: ComponentFixture<PanelEmpleadosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PanelEmpleadosComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PanelEmpleadosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
