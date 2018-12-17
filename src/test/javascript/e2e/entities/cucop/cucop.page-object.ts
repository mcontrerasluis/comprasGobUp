import { element, by, ElementFinder } from 'protractor';

export class CUCOPComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-cucop div table .btn-danger'));
    title = element.all(by.css('jhi-cucop div h2#page-heading span')).first();

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

export class CUCOPUpdatePage {
    pageTitle = element(by.id('jhi-cucop-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    tipoInput = element(by.id('field_tipo'));
    claveCUCOPInput = element(by.id('field_claveCUCOP'));
    partidaEspInput = element(by.id('field_partidaEsp'));
    descripcionInput = element(by.id('field_descripcion'));
    nivelInput = element(by.id('field_nivel'));
    cABMInput = element(by.id('field_cABM'));
    unidadMedInput = element(by.id('field_unidadMed'));
    tipoContrataInput = element(by.id('field_tipoContrata'));
    imagenInput = element(by.id('file_imagen'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setTipoInput(tipo) {
        await this.tipoInput.sendKeys(tipo);
    }

    async getTipoInput() {
        return this.tipoInput.getAttribute('value');
    }

    async setClaveCUCOPInput(claveCUCOP) {
        await this.claveCUCOPInput.sendKeys(claveCUCOP);
    }

    async getClaveCUCOPInput() {
        return this.claveCUCOPInput.getAttribute('value');
    }

    async setPartidaEspInput(partidaEsp) {
        await this.partidaEspInput.sendKeys(partidaEsp);
    }

    async getPartidaEspInput() {
        return this.partidaEspInput.getAttribute('value');
    }

    async setDescripcionInput(descripcion) {
        await this.descripcionInput.sendKeys(descripcion);
    }

    async getDescripcionInput() {
        return this.descripcionInput.getAttribute('value');
    }

    async setNivelInput(nivel) {
        await this.nivelInput.sendKeys(nivel);
    }

    async getNivelInput() {
        return this.nivelInput.getAttribute('value');
    }

    async setCABMInput(cABM) {
        await this.cABMInput.sendKeys(cABM);
    }

    async getCABMInput() {
        return this.cABMInput.getAttribute('value');
    }

    async setUnidadMedInput(unidadMed) {
        await this.unidadMedInput.sendKeys(unidadMed);
    }

    async getUnidadMedInput() {
        return this.unidadMedInput.getAttribute('value');
    }

    async setTipoContrataInput(tipoContrata) {
        await this.tipoContrataInput.sendKeys(tipoContrata);
    }

    async getTipoContrataInput() {
        return this.tipoContrataInput.getAttribute('value');
    }

    async setImagenInput(imagen) {
        await this.imagenInput.sendKeys(imagen);
    }

    async getImagenInput() {
        return this.imagenInput.getAttribute('value');
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

export class CUCOPDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-cUCOP-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-cUCOP'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
