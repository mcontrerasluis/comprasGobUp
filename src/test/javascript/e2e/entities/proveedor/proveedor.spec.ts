/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ProveedorComponentsPage, ProveedorDeleteDialog, ProveedorUpdatePage } from './proveedor.page-object';

const expect = chai.expect;

describe('Proveedor e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let proveedorUpdatePage: ProveedorUpdatePage;
    let proveedorComponentsPage: ProveedorComponentsPage;
    let proveedorDeleteDialog: ProveedorDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Proveedors', async () => {
        await navBarPage.goToEntity('proveedor');
        proveedorComponentsPage = new ProveedorComponentsPage();
        expect(await proveedorComponentsPage.getTitle()).to.eq('comprasGobUpApp.proveedor.home.title');
    });

    it('should load create Proveedor page', async () => {
        await proveedorComponentsPage.clickOnCreateButton();
        proveedorUpdatePage = new ProveedorUpdatePage();
        expect(await proveedorUpdatePage.getPageTitle()).to.eq('comprasGobUpApp.proveedor.home.createOrEditLabel');
        await proveedorUpdatePage.cancel();
    });

    it('should create and save Proveedors', async () => {
        const nbButtonsBeforeCreate = await proveedorComponentsPage.countDeleteButtons();

        await proveedorComponentsPage.clickOnCreateButton();
        await promise.all([
            proveedorUpdatePage.setNombreInput('nombre'),
            proveedorUpdatePage.setRazonSocialInput('razonSocial'),
            proveedorUpdatePage.setRFCInput('rFC'),
            proveedorUpdatePage.setDomicilioFiscalInput('domicilioFiscal'),
            proveedorUpdatePage.setPersonaAutorizadaInput('personaAutorizada'),
            proveedorUpdatePage.setCorreoElectronicoInput('correoElectronico'),
            proveedorUpdatePage.setTelefonoContactoInput('telefonoContacto'),
            proveedorUpdatePage.setTelefonoContactoDosInput('telefonoContactoDos'),
            proveedorUpdatePage.userSelectLastOption()
        ]);
        expect(await proveedorUpdatePage.getNombreInput()).to.eq('nombre');
        expect(await proveedorUpdatePage.getRazonSocialInput()).to.eq('razonSocial');
        expect(await proveedorUpdatePage.getRFCInput()).to.eq('rFC');
        expect(await proveedorUpdatePage.getDomicilioFiscalInput()).to.eq('domicilioFiscal');
        expect(await proveedorUpdatePage.getPersonaAutorizadaInput()).to.eq('personaAutorizada');
        expect(await proveedorUpdatePage.getCorreoElectronicoInput()).to.eq('correoElectronico');
        expect(await proveedorUpdatePage.getTelefonoContactoInput()).to.eq('telefonoContacto');
        expect(await proveedorUpdatePage.getTelefonoContactoDosInput()).to.eq('telefonoContactoDos');
        await proveedorUpdatePage.save();
        expect(await proveedorUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await proveedorComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Proveedor', async () => {
        const nbButtonsBeforeDelete = await proveedorComponentsPage.countDeleteButtons();
        await proveedorComponentsPage.clickOnLastDeleteButton();

        proveedorDeleteDialog = new ProveedorDeleteDialog();
        expect(await proveedorDeleteDialog.getDialogTitle()).to.eq('comprasGobUpApp.proveedor.delete.question');
        await proveedorDeleteDialog.clickOnConfirmButton();

        expect(await proveedorComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
