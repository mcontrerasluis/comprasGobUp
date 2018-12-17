import { element, by, ElementFinder } from 'protractor';

export class ContratoMarcoComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-contrato-marco div table .btn-danger'));
    title = element.all(by.css('jhi-contrato-marco div h2#page-heading span')).first();

    async clickOnCreateButton() {
        await this.createButton.click();
    }

    async clickOnLastDeleteButton() {
        await this.deleteButtons.last().click();
    }

    async countDeleteButtons() {
        return this.deleteButtons.count();
    }

    async getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class ContratoMarcoUpdatePage {
    pageTitle = element(by.id('jhi-contrato-marco-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    autorInput = element(by.id('field_autor'));
    tituloInput = element(by.id('field_titulo'));
    descripcionInput = element(by.id('field_descripcion'));
    fechaVigenciaInput = element(by.id('field_fechaVigencia'));
    montoInput = element(by.id('field_monto'));
    cantidadInput = element(by.id('field_cantidad'));
    imagenInput = element(by.id('file_imagen'));
    contratoInput = element(by.id('file_contrato'));
    anexoInput = element(by.id('file_anexo'));
    convenioInput = element(by.id('file_convenio'));
    requisitosInput = element(by.id('file_requisitos'));
    proveedorSelect = element(by.id('field_proveedor'));
    cUCOPSelect = element(by.id('field_cUCOP'));
    dependenciaSelect = element(by.id('field_dependencia'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setAutorInput(autor) {
        await this.autorInput.sendKeys(autor);
    }

    async getAutorInput() {
        return this.autorInput.getAttribute('value');
    }

    async setTituloInput(titulo) {
        await this.tituloInput.sendKeys(titulo);
    }

    async getTituloInput() {
        return this.tituloInput.getAttribute('value');
    }

    async setDescripcionInput(descripcion) {
        await this.descripcionInput.sendKeys(descripcion);
    }

    async getDescripcionInput() {
        return this.descripcionInput.getAttribute('value');
    }

    async setFechaVigenciaInput(fechaVigencia) {
        await this.fechaVigenciaInput.sendKeys(fechaVigencia);
    }

    async getFechaVigenciaInput() {
        return this.fechaVigenciaInput.getAttribute('value');
    }

    async setMontoInput(monto) {
        await this.montoInput.sendKeys(monto);
    }

    async getMontoInput() {
        return this.montoInput.getAttribute('value');
    }

    async setCantidadInput(cantidad) {
        await this.cantidadInput.sendKeys(cantidad);
    }

    async getCantidadInput() {
        return this.cantidadInput.getAttribute('value');
    }

    async setImagenInput(imagen) {
        await this.imagenInput.sendKeys(imagen);
    }

    async getImagenInput() {
        return this.imagenInput.getAttribute('value');
    }

    async setContratoInput(contrato) {
        await this.contratoInput.sendKeys(contrato);
    }

    async getContratoInput() {
        return this.contratoInput.getAttribute('value');
    }

    async setAnexoInput(anexo) {
        await this.anexoInput.sendKeys(anexo);
    }

    async getAnexoInput() {
        return this.anexoInput.getAttribute('value');
    }

    async setConvenioInput(convenio) {
        await this.convenioInput.sendKeys(convenio);
    }

    async getConvenioInput() {
        return this.convenioInput.getAttribute('value');
    }

    async setRequisitosInput(requisitos) {
        await this.requisitosInput.sendKeys(requisitos);
    }

    async getRequisitosInput() {
        return this.requisitosInput.getAttribute('value');
    }

    async proveedorSelectLastOption() {
        await this.proveedorSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async proveedorSelectOption(option) {
        await this.proveedorSelect.sendKeys(option);
    }

    getProveedorSelect(): ElementFinder {
        return this.proveedorSelect;
    }

    async getProveedorSelectedOption() {
        return this.proveedorSelect.element(by.css('option:checked')).getText();
    }

    async cUCOPSelectLastOption() {
        await this.cUCOPSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async cUCOPSelectOption(option) {
        await this.cUCOPSelect.sendKeys(option);
    }

    getCUCOPSelect(): ElementFinder {
        return this.cUCOPSelect;
    }

    async getCUCOPSelectedOption() {
        return this.cUCOPSelect.element(by.css('option:checked')).getText();
    }

    async dependenciaSelectLastOption() {
        await this.dependenciaSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async dependenciaSelectOption(option) {
        await this.dependenciaSelect.sendKeys(option);
    }

    getDependenciaSelect(): ElementFinder {
        return this.dependenciaSelect;
    }

    async getDependenciaSelectedOption() {
        return this.dependenciaSelect.element(by.css('option:checked')).getText();
    }

    async save() {
        await this.saveButton.click();
    }

    async cancel() {
        await this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}

export class ContratoMarcoDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-contratoMarco-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-contratoMarco'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
